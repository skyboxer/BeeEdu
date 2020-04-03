package pojo;

/**
 * @author cnxjk
 */
public class IndexKey {
    private Integer id;
    private String keyName;
    private String primaryKey;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKey() {
        return keyName;
    }

    public void setKey(String key) {
        this.keyName = key;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public IndexKey(Integer id, String key, String primaryKey) {
        this.id = id;
        this.keyName = key;
        this.primaryKey = primaryKey;
    }
    private IndexKey(){}
}
