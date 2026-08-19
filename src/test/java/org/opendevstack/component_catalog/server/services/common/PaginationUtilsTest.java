package org.opendevstack.component_catalog.server.services.common;

import org.junit.jupiter.api.Test;
import org.opendevstack.component_catalog.server.model.Pagination;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
        assertThat(pagination.getPage()).isZero();
        assertThat(pagination.getSize()).isEqualTo(10);
        assertThat(pagination.getTotalElements()).isEqualTo(25);
        assertThat(pagination.getTotalPages()).isEqualTo(3);

        assertThat(pagination.getNext().isPresent()).isTrue();
        assertThat(pagination.getNext().get())
                .hasToString("https://component-catalog.myserver.com/project/components?page=1&size=10");

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
        assertThat(pagination.getNext().get())
                .hasToString("https://component-catalog.myserver.com/project/components?page=2&size=10");

        assertThat(pagination.getPrevious().isPresent()).isTrue();
        assertThat(pagination.getPrevious().get())
                .hasToString("https://component-catalog.myserver.com/project/components?page=0&size=10");
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
        assertThat(pagination.getPrevious().get())
                .hasToString("https://component-catalog.myserver.com/project/components?page=1&size=10");
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
        assertThat(pagination.getTotalPages()).isZero();

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

    @Test
    void givenAListOfElements_whenPaginate_thenReturnProperPage() {
        // given
        int page = 1;
        int size = 2;

        List<String> elements = List.of("a", "b", "c", "d", "e");

        String basePath = "https://component-catalog.myserver.com/project/paginations";

        // when
        var pagination = PaginationUtils.buildPagination(page, size, elements, basePath);

        // then
        assertThat(pagination.getData()).hasSize(2);
        assertThat(pagination.getData()).containsExactly("c", "d");
        assertThat(pagination.getPagination().getTotalElements()).isEqualTo(5);
        assertThat(pagination.getPagination().getTotalPages()).isEqualTo(3);
        assertThat(pagination.getPagination().getNext().isPresent()).isTrue();
        assertThat(pagination.getPagination().getNext().get())
                .hasToString("https://component-catalog.myserver.com/project/paginations?page=2&size=2");
        assertThat(pagination.getPagination().getPrevious().isPresent()).isTrue();
        assertThat(pagination.getPagination().getPrevious().get())
                .hasToString("https://component-catalog.myserver.com/project/paginations?page=0&size=2");
    }

    @Test
    void givenInvalidPage_whenPaginate_thenThrowsException() {
        // given
        int page = -1;
        int size = 2;

        List<String> elements = List.of("a", "b", "c", "d", "e");

        String basePath = "https://component-catalog.myserver.com/project/paginations";

        // when
        var exception = assertThatThrownBy(() -> PaginationUtils.buildPagination(page, size, elements, basePath))
                .isInstanceOf(IllegalArgumentException.class)
                .actual();

        // then
        assertThat(exception.getMessage()).isEqualTo("Page must be greater than or equal to 0");
    }

    @Test
    void givenInvalidSize_whenPaginate_thenThrowsException() {
        // given
        int page = 0;
        int size = -1;

        List<String> elements = List.of("a", "b", "c", "d", "e");

        String basePath = "https://component-catalog.myserver.com/project/paginations";

        // when
        var exception = assertThatThrownBy(() -> PaginationUtils.buildPagination(page, size, elements, basePath))
                .isInstanceOf(IllegalArgumentException.class)
                .actual();

        // then
        assertThat(exception.getMessage()).isEqualTo("Size must be greater than 0");
    }

}
