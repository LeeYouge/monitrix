package uk.bl.monitrix.alerts;

import uk.bl.monitrix.Alert;
import uk.bl.monitrix.heritrix.LogEntry;

public class TooManyPathSegmentsRule implements AlertRule {

	private static final String ALERT_NAME = "Too Many Path Segments";
	
	private static final String ALERT_DESCRIPTION = "The following URL has too many path segments: ";
	
	@Override
	public Alert check(LogEntry entry) {
		String[] pathSegments = entry.getURL().split("/");
		if (pathSegments.length > AlertProperties.TOO_MANY_PATH_SEGMENTS_THRESHOLD)
			return new Alert(entry.getHost(), ALERT_NAME, ALERT_DESCRIPTION + entry.getURL());
		else
			return null;
	}

}
