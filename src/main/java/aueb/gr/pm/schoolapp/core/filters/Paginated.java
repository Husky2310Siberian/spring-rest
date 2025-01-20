package aueb.gr.pm.schoolapp.core.filters;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import java.util.List;

@Getter
@Setter
public class Paginated<T> {

    List<T> data;
    long totalElements;
    int totalPages;
    int numberOfElements;
    int currentPage;
    int pageSize;

    public Paginated(Page<T> page) {
        this.data = data;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.numberOfElements = numberOfElements;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }
}
