package org.opendevstack.component_catalog.server.services.common;

import org.junit.jupiter.api.Test;
import org.opendevstack.component_catalog.server.model.Pagination;

import static org.assertj.core.api.Assertions.assertThat;

class PaginationUtilsTest {

    @Test
    void givenFirstPage_whenBuildPagination_thenHasNextAndNoPrevious() {
        // given
        int page = 0;
        int size = 10;
        int totalElements = 25;
        String basePath = "https://component-catalog.myserver.com/project/components";

        // when
        Pagination pagination = PaginationUtils.buildPagination(page, size, totalElements, basePath);

        // then
        assertThat(pagination.getPage()).isEqualTo(0);
        assertThat(pagination.getSize()).isEqualTo(10);
        assertThat(pagination.getTotalElements()).isEqualTo(25);
        assertThat(pagination.getTotalPages()).isEqualTo(3);

        assertThat(pagination.getNext().isPresent()).isTrue();
        assertThat(pagination.getNext().get().toString())
                .isEqualTo("https://component-catalog.myserver.com/project/components?page=1&size=10");

        assertThat(pagination.getPrevious().isPresent()).isTrue();
        assertThat(pagination.getPrevious().get()).isNull();
    }

    @Test
    void givenMiddlePage_whenBuildPagination_thenHasNextAndPrevious() {
        // given
        int page = 1;
        int size = 10;
        int totalElements = 30;
        String basePath = "https://component-catalog.myserver.com/project/components";

        // when
        Pagination pagination = PaginationUtils.buildPagination(page, size, totalElements, basePath);

        // then
        assertThat(pagination.getTotalPages()).isEqualTo(3);

        assertThat(pagination.getNext().isPresent()).isTrue();
        assertThat(pagination.getNext().get().toString())
                .isEqualTo("https://component-catalog.myserver.com/project/components?page=2&size=10");

        assertThat(pagination.getPrevious().isPresent()).isTrue();
        assertThat(pagination.getPrevious().get().toString())
                .isEqualTo("https://component-catalog.myserver.com/project/components?page=0&size=10");
    }

    @Test
    void givenLastPage_whenBuildPagination_thenHasNoNextAndHasPrevious() {
        // given
        int page = 2;
        int size = 10;
        int totalElements = 30;
        String basePath = "https://component-catalog.myserver.com/project/components";

        // when
        Pagination pagination = PaginationUtils.buildPagination(page, size, totalElements, basePath);

        // then
        assertThat(pagination.getTotalPages()).isEqualTo(3);

        assertThat(pagination.getNext().isPresent()).isTrue();
        assertThat(pagination.getNext().get()).isNull();

        assertThat(pagination.getPrevious().isPresent()).isTrue();
        assertThat(pagination.getPrevious().get().toString())
                .isEqualTo("https://component-catalog.myserver.com/project/components?page=1&size=10");
    }


    @Test
    void givenZeroSize_whenBuildPagination_thenTotalPagesIsZero() {
        // given
        int page = 0;
        int size = 0;
        int totalElements = 100;
        String basePath = "https://component-catalog.myserver.com/project/components";

        // when
        Pagination pagination = PaginationUtils.buildPagination(page, size, totalElements, basePath);

        // then
        assertThat(pagination.getTotalPages()).isEqualTo(0);

        assertThat(pagination.getNext().isPresent()).isTrue();
        assertThat(pagination.getNext().get()).isNull();

        assertThat(pagination.getPrevious().isPresent()).isTrue();
        assertThat(pagination.getPrevious().get()).isNull();
    }

    @Test
    void givenSinglePage_whenBuildPagination_thenNoNextNorPrevious() {
        // given
        int page = 0;
        int size = 10;
        int totalElements = 5;
        String basePath = "https://component-catalog.myserver.com/project/components";

        // when
        Pagination pagination = PaginationUtils.buildPagination(page, size, totalElements, basePath);

        // then
        assertThat(pagination.getTotalPages()).isEqualTo(1);

        assertThat(pagination.getNext().isPresent()).isTrue();
        assertThat(pagination.getNext().get()).isNull();

        assertThat(pagination.getPrevious().isPresent()).isTrue();
        assertThat(pagination.getPrevious().get()).isNull();
    }
}
