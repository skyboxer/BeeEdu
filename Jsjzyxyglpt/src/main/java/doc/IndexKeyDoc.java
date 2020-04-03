package doc;

import pojo.IndexKey;

import java.util.List;

/**
 * @author cnxjk
 */
public interface IndexKeyDoc {
    List<IndexKey> getIndexKey(IndexKey indexKey);

    int updateIndexKey(IndexKey indexKey);

    int delIndexKey(IndexKey indexKey);

    int addIndexKey(IndexKey indexKey);
}
