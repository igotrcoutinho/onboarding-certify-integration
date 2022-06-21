package io.smilego.onboarding.certify.service.api.application.configuration.property;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.TreeSet;

import io.micronaut.context.annotation.ConfigurationProperties;

@ConfigurationProperties("certify")
public class ApplicationProperties {

    private String url;

    private String costumerId;

    private ProcessProperties process = new ProcessProperties();
    private AuthenticationProperties authentication = new AuthenticationProperties();
    private ErrorProperties error = new ErrorProperties();
    private SecurityProperties security = new SecurityProperties();

    public ProcessProperties getProcess() {
        return process;
    }

    public void setProcess(ProcessProperties process) {
        this.process = process;
    }

    public AuthenticationProperties getAuthentication() {
        return authentication;
    }

    public void setAuthentication(AuthenticationProperties authentication) {
        this.authentication = authentication;
    }

    public ErrorProperties getError() {
        return error;
    }

    public void setError(ErrorProperties error) {
        this.error = error;
    }

    public SecurityProperties getSecurity() {
        return security;
    }

    public void setSecurity(SecurityProperties security) {
        this.security = security;
    }

    public String getUrl() {  return url; }

    public void setUrl(String url) { this.url = url; }

    public String getCostumerId() {  return costumerId; }

    public void CostumerId(String costumerId) { this.costumerId = costumerId; }

    @Override
    public String toString() {
        return "authentication=[" + authentication + "], url=[" + url + "], error=[" + error + "]";
    }

    @ConfigurationProperties("authentication")
    public static class AuthenticationProperties {
        private String apiKey;

        public String getApiKey() {
            return apiKey;
        }

        public void setApiKey(String apiKey) {
            this.apiKey = apiKey;
        }

        @Override
        public String toString() {
            return "apiKey=" + apiKey;
        }
    }

    @ConfigurationProperties("process")
    public static class ProcessProperties {
        private String url;
        private TreeSet<BigDecimal> decisionLimiters = new TreeSet<>();
        private ProcessRetryProperties retry = new ProcessRetryProperties();

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public TreeSet<BigDecimal> getDecisionLimiters() {
            return decisionLimiters;
        }

        public void setDecisionLimiters(TreeSet<BigDecimal> decisionLimiters) {
            this.decisionLimiters = decisionLimiters;
        }

        public ProcessRetryProperties getRetry() {
            return retry;
        }

        public void setRetry(ProcessRetryProperties retry) {
            this.retry = retry;
        }

        @Override
        public String toString() {
            return "url=" + url + ", retry=[" + retry + "], " + ", decisionLimiters=[" + decisionLimiters + "]";
        }

        @ConfigurationProperties("retry")
        public static class ProcessRetryProperties {
            private Integer interval = 5000;
            private Integer maxAttempts = 20;

            public Integer getInterval() {
                return interval;
            }

            public void setInterval(Integer interval) {
                this.interval = interval;
            }

            @Override
            public String toString() {
                return "interval=" + interval;
            }

            public Integer getMaxAttempts() {
                return maxAttempts;
            }

            public void setMaxAttempts(Integer maxAttempts) {
                this.maxAttempts = maxAttempts;
            }

        }

    }

    @ConfigurationProperties("error")
    public static class ErrorProperties {
        private ErrorRetryProperties retry = new ErrorRetryProperties();

        public ErrorRetryProperties getRetry() {
            return retry;
        }

        public void setRetry(ErrorRetryProperties retry) {
            this.retry = retry;
        }

        @Override
        public String toString() {
            return "retry=[" + retry + "]";
        }

        @ConfigurationProperties("retry")
        public static class ErrorRetryProperties {
            private Integer maxAttempts = 5;
            private Integer interval = 5000;

            public Integer getMaxAttempts() {
                return maxAttempts;
            }

            public void setMaxAttempts(Integer maxAttempts) {
                this.maxAttempts = maxAttempts;
            }

            public Integer getInterval() {
                return interval;
            }

            public void setInterval(Integer interval) {
                this.interval = interval;
            }

            @Override
            public String toString() {
                return "attempts=" + maxAttempts + ", interval=" + interval;
            }

        }

    }

    @ConfigurationProperties("security")
    public static class SecurityProperties {
        private String userSystemToken;

        public String getUserSystemToken() {
            return userSystemToken;
        }

        public void setUserSystemToken(String userSystemToken) {
            this.userSystemToken = userSystemToken;
        }

        @Override
        public String toString() {
            return "userSystemToken=" + userSystemToken;
        }

    }

}
