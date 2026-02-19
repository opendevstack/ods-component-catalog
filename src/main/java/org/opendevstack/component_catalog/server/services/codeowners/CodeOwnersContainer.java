package org.opendevstack.component_catalog.server.services.codeowners;

import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
public class CodeOwnersContainer {
    @Getter
    private List<String> codeOwners;

    public CodeOwnersContainer(String baseText) {
        populateCodeOwners(baseText);
    }

    @SneakyThrows
    private void populateCodeOwners(String baseText) {
        var codeOwnersSet = new HashSet<String>();

        BufferedReader reader = new BufferedReader(new StringReader(baseText));
        String line;
        while ((line = reader.readLine()) != null) {
            Set<String> codeOwnersLine = extract(line);

            codeOwnersSet.addAll(codeOwnersLine);
        }

        this.codeOwners = codeOwnersSet.stream()
                .map(this::removeEmailSuffix)
                .toList();
    }

    private String removeEmailSuffix(String email) {
        var emailParts = email.split("@");

        return emailParts[0];
    }

    private Set<String> extract(String baseTextLine) {
        Set<String> codeOwnersSet = new HashSet<>();

        String[] tokens = baseTextLine.split(" ");

        for (int  i = 0; i < tokens.length; i++) {
            if (i == 0) {
                log.debug("Base token is file pattern, ignoring it. Token: {}", tokens[i]);
            } else {
                log.debug("Processing token: {}", tokens[i]);

                codeOwnersSet.add(tokens[i]);
            }
        }

        return codeOwnersSet;
    }
}
