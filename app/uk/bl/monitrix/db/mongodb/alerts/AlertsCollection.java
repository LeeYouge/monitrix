package uk.bl.monitrix.db.mongodb.alerts;

import java.util.Iterator;

import uk.bl.monitrix.db.mongodb.MongoProperties;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

public class AlertsCollection {
	
	private DBCollection collection;
	
	public AlertsCollection(DB db) {
		this.collection = db.getCollection(MongoProperties.COLLECTION_ALERTS);
	}
	
	public void insert(AlertsDBO alert) {
		collection.insert(alert.dbo);
	}
	
	public Iterator<AlertsDBO> all() {
		final DBCursor cursor = collection.find();
		
		return new Iterator<AlertsDBO>() {		
			@Override
			public boolean hasNext() {
				return cursor.hasNext();
			}

			@Override
			public AlertsDBO next() {
				return new AlertsDBO(cursor.next());
			}

			@Override
			public void remove() {
				cursor.remove();
			}
		};
	}

}
