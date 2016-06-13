package pragma.rocks.dataIdentity.mongo;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Repository;

import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;

@Repository
public class PermanentRepository {

	@Autowired
	private GridFsTemplate repoTemplate;

	public String addDO(InputStream inputStream, String file_name, String content_type, DBObject metadata) {
		return repoTemplate.store(inputStream, file_name, content_type, metadata).getId().toString();
	}

	public List<GridFSDBFile> listAll() {
		return repoTemplate.find(null);
	}

	public GridFSDBFile findDOByID(String id) {
		return repoTemplate.findOne(Query.query(Criteria.where("_id").is(id)));
	}

	public boolean deleteDOByID(String id) {
		if (repoTemplate.findOne(Query.query(Criteria.where("_id").is(id))).equals(null))
			return false;
		else {
			repoTemplate.delete(Query.query(Criteria.where("_id").is(id)));
			return true;
		}
	}

	public boolean existDOByID(String id) {
		boolean result = repoTemplate.findOne(Query.query(Criteria.where("_id").is(id))).equals(null);
		return !result;
	}
}