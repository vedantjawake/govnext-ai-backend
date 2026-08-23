package com.govnext.backend.dto;

public class AuthDto {

    public static class LoginRequest {
        private String email;
        private String password;

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }

    public static class RegisterRequest {
        private String name;
        private String email;
        private String password;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }

    public static class AuthResponse {
        private String token;
        private String email;
        private String name;

        public AuthResponse(String token, String email, String name) {
            this.token = token;
            this.email = email;
            this.name = name;
        }

        public String getToken() { return token; }
        public String getEmail() { return email; }
        public String getName() { return name; }
    }
}