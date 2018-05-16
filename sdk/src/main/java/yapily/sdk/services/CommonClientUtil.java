package yapily.sdk.services;

import com.google.common.base.Preconditions;

public class CommonClientUtil {

    /**
     * If the specified root URL does not end with a "/" then a "/" is added to the end.
     */
    static String normalizeRootUrl(String rootUrl) {
        Preconditions.checkNotNull(rootUrl, "root URL cannot be null.");
        if (!rootUrl.endsWith("/")) {
            rootUrl += "/";
        }
        return rootUrl;
    }

    /**
     * If the specified service path does not end with a "/" then a "/" is added to the end. If the
     * specified service path begins with a "/" then the "/" is removed.
     */
    static String normalizeServicePath(String servicePath) {
        Preconditions.checkNotNull(servicePath, "service path cannot be null");
        if (servicePath.length() == 1) {
            Preconditions.checkArgument("/".equals(servicePath), "service path must equal \"/\" if it is of length 1.");
            servicePath = "";
        } else if (servicePath.length() > 0) {
            if (!servicePath.endsWith("/") && !servicePath.contains("?")) {
                servicePath += "/";
            }
            if (servicePath.startsWith("/")) {
                servicePath = servicePath.substring(1);
            }
        }
        return servicePath;
    }

}
