package yapily.sdk.client;

import java.util.HashMap;
import java.util.Map;

public class ValueMap {

    private static final String INSTITUTION_ID_PLACEHOLDER = "institutionId";
    private static final String USER_UUID_PLACEHOLDER = "userUuid";
    private static final String ACCOUNT_ID_PLACEHOLDER = "accountId";
    private static final String CONSENT_TOKEN_PLACEHOLDER = "consentToken";

    private Map<String, String> valueMap = new HashMap<>();

    public ValueMap withInstitutionId(String institutionId) {
        valueMap.put(INSTITUTION_ID_PLACEHOLDER, institutionId);
        return this;
    }

    public ValueMap withUserUuid(String userUuid) {
        valueMap.put(USER_UUID_PLACEHOLDER, userUuid);
        return this;
    }

    public ValueMap withAccountId(String accountId) {
        valueMap.put(ACCOUNT_ID_PLACEHOLDER, accountId);
        return this;
    }

    public ValueMap withConsentToken(String consentToken) {
        valueMap.put(CONSENT_TOKEN_PLACEHOLDER, consentToken);
        return this;
    }

    public Map<String, String> toMap() {
        return valueMap;
    }
}
