package com.sonnesen.todolist.domain.pagination;

import java.util.List;

public record Pagination<T>(int page, int size, int total, int totalPages, List<T> items) {

}
