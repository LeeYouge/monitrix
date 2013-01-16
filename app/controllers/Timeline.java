package controllers;

import java.util.List;

import controllers.mapping.TimeseriesValueMapper;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import uk.bl.monitrix.Global;
import uk.bl.monitrix.analytics.CrawlStatsAnalytics;
import uk.bl.monitrix.analytics.TimeseriesValue;
import uk.bl.monitrix.model.CrawlLog;
import uk.bl.monitrix.model.CrawlStats;

public class Timeline extends Controller {

	private static CrawlLog log = Global.getBackend().getCrawlLog();
	
	private static CrawlStats stats = Global.getBackend().getCrawlStats();
	
	public static Result index() {
		return ok(views.html.timeline.index.render(log));
	}
	
	public static Result getDatavolume() {
		List<TimeseriesValue> datavolume = CrawlStatsAnalytics.getDatavolumeHistory(stats.getCrawlStats(), getMaxPoints());
		return ok(Json.toJson(TimeseriesValueMapper.map(datavolume)));
	}
	
	public static Result getURLs() {
		List<TimeseriesValue> urls = CrawlStatsAnalytics.getCrawledURLsHistory(stats.getCrawlStats(), getMaxPoints());
		return ok(Json.toJson(TimeseriesValueMapper.map(urls)));
	}
	
	public static Result getNewHostsCrawled() {
		List<TimeseriesValue> newHosts = CrawlStatsAnalytics.getNewHostsCrawledHistory(stats.getCrawlStats(), getMaxPoints());
		return ok(Json.toJson(TimeseriesValueMapper.map(newHosts)));
	}
	
	public static Result getCompletedHosts() {
		List<TimeseriesValue> completedHosts = CrawlStatsAnalytics.getCompletedHostsHistory(stats.getCrawlStats(), getMaxPoints());
		return ok(Json.toJson(TimeseriesValueMapper.map(completedHosts)));
	}
	
	private static int getMaxPoints() {
		String[] param = request().queryString().get("maxpoints");
		if (param == null)
			return 100;
		
		if (param.length < 1)
			return 100;
		
		try {
			int maxPoints = Integer.parseInt(param[0]);
			if (maxPoints < 100)
				maxPoints = 100;
			
			return maxPoints;
		} catch (Throwable t) {
			return 100;
		}
	}
	
}
