package doc;

import pojo.Lable;

import java.util.List;

/**
 * @author cnxjk
 */
public interface LableDoc {
    List<Lable> getLable(Lable lable);

    int delLable(Lable lable);

    int updateLable(Lable lable);

    int addLable(Lable lable);
}
