package com.company.project.model;

import javax.persistence.*;

@Table(name = "searcher_websites")
public class SearcherWebsites {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 网址
     */
    @Column(name = "webUrl")
    private String weburl;

    /**
     * 网站名称
     */
    @Column(name = "webName")
    private String webname;

    /**
     * 网站描述
     */
    @Column(name = "webDoc")
    private String webdoc;

    /**
     * 网站Logo
     */
    @Column(name = "webLogo")
    private String weblogo;

    /**
     * 网站类型
     */
    @Column(name = "webType")
    private String webtype;

    /**
     * 网站评分
     */
    private Integer cent;

    /**
     * 网址来源网站
     */
    @Column(name = "fromUrl")
    private String fromurl;

    private Integer other2;

    private Integer other3;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取网址
     *
     * @return webUrl - 网址
     */
    public String getWeburl() {
        return weburl;
    }

    /**
     * 设置网址
     *
     * @param weburl 网址
     */
    public void setWeburl(String weburl) {
        this.weburl = weburl;
    }

    /**
     * 获取网站名称
     *
     * @return webName - 网站名称
     */
    public String getWebname() {
        return webname;
    }

    /**
     * 设置网站名称
     *
     * @param webname 网站名称
     */
    public void setWebname(String webname) {
        this.webname = webname;
    }

    /**
     * 获取网站描述
     *
     * @return webDoc - 网站描述
     */
    public String getWebdoc() {
        return webdoc;
    }

    /**
     * 设置网站描述
     *
     * @param webdoc 网站描述
     */
    public void setWebdoc(String webdoc) {
        this.webdoc = webdoc;
    }

    /**
     * 获取网站Logo
     *
     * @return webLogo - 网站Logo
     */
    public String getWeblogo() {
        return weblogo;
    }

    /**
     * 设置网站Logo
     *
     * @param weblogo 网站Logo
     */
    public void setWeblogo(String weblogo) {
        this.weblogo = weblogo;
    }

    /**
     * 获取网站类型
     *
     * @return webType - 网站类型
     */
    public String getWebtype() {
        return webtype;
    }

    /**
     * 设置网站类型
     *
     * @param webtype 网站类型
     */
    public void setWebtype(String webtype) {
        this.webtype = webtype;
    }

    /**
     * 获取网站评分
     *
     * @return cent - 网站评分
     */
    public Integer getCent() {
        return cent;
    }

    /**
     * 设置网站评分
     *
     * @param cent 网站评分
     */
    public void setCent(Integer cent) {
        this.cent = cent;
    }

    /**
     * 获取网址来源网站
     *
     * @return fromUrl - 网址来源网站
     */
    public String getFromurl() {
        return fromurl;
    }

    /**
     * 设置网址来源网站
     *
     * @param fromurl 网址来源网站
     */
    public void setFromurl(String fromurl) {
        this.fromurl = fromurl;
    }

    /**
     * @return other2
     */
    public Integer getOther2() {
        return other2;
    }

    /**
     * @param other2
     */
    public void setOther2(Integer other2) {
        this.other2 = other2;
    }

    /**
     * @return other3
     */
    public Integer getOther3() {
        return other3;
    }

    /**
     * @param other3
     */
    public void setOther3(Integer other3) {
        this.other3 = other3;
    }
}