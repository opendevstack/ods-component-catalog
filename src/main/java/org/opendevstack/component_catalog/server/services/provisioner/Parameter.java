package org.opendevstack.component_catalog.server.services.provisioner;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.NonNull;

import java.util.List;

@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"name", "values"})
public class Parameter implements Comparable<Parameter> {
    private String name;
    private List<String> values;

    @Override
    public int compareTo(@NonNull Parameter other) {
        if (this.name == null && other.name == null) {
            return 0;
        }
        if (this.name == null) {
            return -1;
        }
        if (other.name == null) {
            return 1;
        }
        return this.name.compareTo(other.name);
    }
}

