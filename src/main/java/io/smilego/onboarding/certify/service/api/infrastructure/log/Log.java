package io.smilego.onboarding.certify.service.api.infrastructure.log;

public class Log {

    public static class Builder {
        private String header;
        private String rows;
        
        public Builder() {
            this.header = "";
            this.rows = "";
        }

        public Builder setHeader(String header) {
            this.header = " => " + header;
            return this;
        }

        public Builder addRow(String row) {
            this.rows += "\n - " + row;
            return this;
        }

        public Builder addRow(String description, Object value) {
            this.rows += "\n - " + description + ": " + String.valueOf(value);
            return this;
        }

        public String build() {
            return this.header + this.rows;
        }

        public static Builder of() {
            return new Builder();
        }
    }

}
