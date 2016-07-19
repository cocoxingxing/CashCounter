package pers.coco.tools;

import java.util.Hashtable;
import java.util.Set;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import pers.coco.commons.Consts;
import pers.coco.model.Commodity;

/**
 * Created by Administrator on 2016/7/19.
 */

public class FileHelperTest {
    @Test
    public void should_return_empty_when_read_nonexist_file() throws Exception {
        String filePath = "test.json";
        assertEquals("", FileHelper.readFile(filePath));
    }

}