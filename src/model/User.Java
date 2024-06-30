public Class User {
    private String nickname;
    private String email;
    private String password;
    private int balance;

    public User(String nickname, String email, String password, int balance) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.balance = balance;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getBalance() {
        return balance;
    }
}