package cn.huan.community.community.domain;

public class Account {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.id
     *
     * @mbg.generated Thu Jul 23 18:10:17 CST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.account_id
     *
     * @mbg.generated Thu Jul 23 18:10:17 CST 2020
     */
    private String accountId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.user_name
     *
     * @mbg.generated Thu Jul 23 18:10:17 CST 2020
     */
    private String userName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.token
     *
     * @mbg.generated Thu Jul 23 18:10:17 CST 2020
     */
    private String token;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.gmt_create
     *
     * @mbg.generated Thu Jul 23 18:10:17 CST 2020
     */
    private Long gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.gmt_modified
     *
     * @mbg.generated Thu Jul 23 18:10:17 CST 2020
     */
    private Long gmtModified;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.avatar_url
     *
     * @mbg.generated Thu Jul 23 18:10:17 CST 2020
     */
    private String avatarUrl;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.id
     *
     * @return the value of account.id
     *
     * @mbg.generated Thu Jul 23 18:10:17 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.id
     *
     * @param id the value for account.id
     *
     * @mbg.generated Thu Jul 23 18:10:17 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.account_id
     *
     * @return the value of account.account_id
     *
     * @mbg.generated Thu Jul 23 18:10:17 CST 2020
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.account_id
     *
     * @param accountId the value for account.account_id
     *
     * @mbg.generated Thu Jul 23 18:10:17 CST 2020
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.user_name
     *
     * @return the value of account.user_name
     *
     * @mbg.generated Thu Jul 23 18:10:17 CST 2020
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.user_name
     *
     * @param userName the value for account.user_name
     *
     * @mbg.generated Thu Jul 23 18:10:17 CST 2020
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.token
     *
     * @return the value of account.token
     *
     * @mbg.generated Thu Jul 23 18:10:17 CST 2020
     */
    public String getToken() {
        return token;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.token
     *
     * @param token the value for account.token
     *
     * @mbg.generated Thu Jul 23 18:10:17 CST 2020
     */
    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.gmt_create
     *
     * @return the value of account.gmt_create
     *
     * @mbg.generated Thu Jul 23 18:10:17 CST 2020
     */
    public Long getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.gmt_create
     *
     * @param gmtCreate the value for account.gmt_create
     *
     * @mbg.generated Thu Jul 23 18:10:17 CST 2020
     */
    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.gmt_modified
     *
     * @return the value of account.gmt_modified
     *
     * @mbg.generated Thu Jul 23 18:10:17 CST 2020
     */
    public Long getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.gmt_modified
     *
     * @param gmtModified the value for account.gmt_modified
     *
     * @mbg.generated Thu Jul 23 18:10:17 CST 2020
     */
    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.avatar_url
     *
     * @return the value of account.avatar_url
     *
     * @mbg.generated Thu Jul 23 18:10:17 CST 2020
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.avatar_url
     *
     * @param avatarUrl the value for account.avatar_url
     *
     * @mbg.generated Thu Jul 23 18:10:17 CST 2020
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl == null ? null : avatarUrl.trim();
    }
}