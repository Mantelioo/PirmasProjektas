package lt.vu.mybatis.model;

public class Shop {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.SHOP.ID
     *
     * @mbg.generated Sun Apr 11 17:25:29 EEST 2021
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.SHOP.NAME
     *
     * @mbg.generated Sun Apr 11 17:25:29 EEST 2021
     */
    private String name;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.SHOP.ID
     *
     * @return the value of PUBLIC.SHOP.ID
     *
     * @mbg.generated Sun Apr 11 17:25:29 EEST 2021
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.SHOP.ID
     *
     * @param id the value for PUBLIC.SHOP.ID
     *
     * @mbg.generated Sun Apr 11 17:25:29 EEST 2021
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.SHOP.NAME
     *
     * @return the value of PUBLIC.SHOP.NAME
     *
     * @mbg.generated Sun Apr 11 17:25:29 EEST 2021
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.SHOP.NAME
     *
     * @param name the value for PUBLIC.SHOP.NAME
     *
     * @mbg.generated Sun Apr 11 17:25:29 EEST 2021
     */
    public void setName(String name) {
        this.name = name;
    }
}