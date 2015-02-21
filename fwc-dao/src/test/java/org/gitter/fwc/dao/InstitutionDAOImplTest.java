package org.gitter.fwc.dao;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class InstitutionDAOImplTest {
    private static final InstitutionDAO dao = new InstitutionDAOImpl();

    @Test
    public void test_findByKeyword() {
        String keyword = "Milwaukee WI";

        List<Map<String, String>> result = dao.findByKeyword(keyword);

        Assert.assertEquals(36, result.size());
        for (Map<String, String> map : result) {
            Assert.assertTrue("Milwaukee".equals(map.get("CITY")) ||
                    map.get("ADDR").contains("Milwaukee") ||
                    map.get("COUNTYNM").contains("Milwaukee") ||
                    map.get("INSTNM").contains("Milwaukee"));
        }
    }
}