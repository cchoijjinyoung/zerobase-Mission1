package com.zerobase.misson1.service;

import com.zerobase.misson1.dto.BookmarkGroup;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class BookmarkGroupServiceTest {
    BookmarkGroupService bookmarkGroupService = new BookmarkGroupService();
    @Test
    void test() {
        String name = "테스트";
        int sequence = 11;
        bookmarkGroupService.insert(name, sequence);
        List<BookmarkGroup> list = bookmarkGroupService.list();
        BookmarkGroup bookmarkGroup = list.get(0);
        Assertions.assertEquals("테스트", bookmarkGroup.getName());
        Assertions.assertEquals(11, bookmarkGroup.getSequence());
    }
}