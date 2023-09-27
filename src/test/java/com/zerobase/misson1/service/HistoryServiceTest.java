package com.zerobase.misson1.service;

import com.zerobase.misson1.dto.History;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;


class HistoryServiceTest {
    HistoryService historyService = new HistoryService();

    @Test
    void listTest() {
        List<History> list = historyService.list();
        for (History history : list) {
            System.out.print(history.getId() + " ");
            System.out.print(history.getLAT() + " ");
            System.out.print(history.getLNT() + " ");
            System.out.print(history.getWORK_DTTM());
            System.out.println();
        }
    }

    @Test
    void insertTest() {
        String lat = "36.123456";
        String lnt = "127.123456";

        historyService.insert(lat, lnt);

        String targetLat = historyService.list().get(0).getLAT();
        String targetLnt = historyService.list().get(0).getLNT();

        Assertions.assertEquals(targetLat, lat);
        Assertions.assertEquals(targetLnt, lnt);
    }
}