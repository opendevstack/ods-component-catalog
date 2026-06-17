package org.opendevstack.component_catalog.server.services.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Pair<K, V> {
    private K left;
    private V right;
}
