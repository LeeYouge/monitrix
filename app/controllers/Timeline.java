package controllers;

import global.Global;
import controllers.filter.TimeseriesValueFilter;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import uk.bl.monitrix.CrawlStatistics;

public class Timeline extends Controller {

	private static CrawlStatistics stats = Global.getCrawlStatistics();
	
	public static Result index() {
		return ok(views.html.timeline.index.render(stats));
	}
	
	public static Result getDatavolume() {
		return ok(Json.toJson(TimeseriesValueFilter.map(stats.getDatavolumeHistory(100))));
	}
	
	public static Result getURLs() {
		return ok(Json.toJson(TimeseriesValueFilter.map(stats.getCrawledURLsHistory(100))));
	}
	
	public static Result getNewHostsCrawled() {
		return ok(Json.toJson(TimeseriesValueFilter.map(stats.getNewHostsCrawledHistory(100))));
	}
	
}
