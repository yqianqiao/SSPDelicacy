package cn.com.quickpark.sspdelicacy.constants;

import cn.com.quickpark.sspdelicacy.bean.BaseResult;

/**
 * Created by y on 2018/7/9.
 */

public class User extends BaseResult{

    private int id;//主键

    private long createTime;

    private long updateTime;

    private String userId;	//用户Id

    private String password;	//加密密码

    private int sex;	//性别:0男，1女

    private String birthDay;	//生日

    private String idcard;	//身份证号

    private String idcardPicFront;	//身份证正面图片url

    private String idcardPicVerso;	//身份证反面图片url

    private String signature;	//个性签名

    private String avatarPic;	//用户头像url

    private String tpWxOpenid;	//第三方登录微信openId

    private String tpWxAvatar;	//第三方登录微信头像url

    private String tpQqOpenid;	//第三方登录QQopenId

    private String tpQqAvatar;	//第三方登录qq头像url

    private String tpZfbOpenid;	//第三方登录支付宝openId

    private String tpZfbAvatar;	//第三方登录支付宝头像url

    private String wxOpenid;	//微信服务号openid

    private String wxNickname;	//微信昵称

    private int realNameStatus;	//实名认证状态 0 未认证 1 认证中 2 认证通过 3认证失败

    private String realNameNopassReason;	//实名认证不通过原因

    private long realNameCommitTime;	//实名提交时间

    private long realNameAuditTime;	//实名认证时间

    private String carManagerBg;//ssp车辆管理主页的背景url

    /**
     * 用户角色信息
     */
    private int projectRole;	//项目角色(MEP/SSP/CIPS/IPSP/游客)

    private int detailRole;		//细分角色(巡查员/SSP/收费员/客服/电销)

    private String mepMembership;	//mep会籍

    private long mepLastLoginTime;	//mep最后登录时间

    private String sspMembership;	//ssp会籍

    private long sspLastLoginTime;	//ssp最后登录时间

    private String cipsMembership;	//cips会籍

    private long cipsLastLoginTime;	//cips最后登录时间

    private String ipspMembership;	//ipsp会籍

    private long ipspLastLoginTime;	//ipsp最后登录时间

    private int cashierType;	//收费员类型(1.分配2.定价3.竞价4.快乐增收)

    private int referrerAwardStatus;//推荐奖励获取状态,0.领取1.未领取2.过期

    private int detailRoleApplyStatus;	//细分角色申请状态

    private String companyNo;  //公司编号

    /**
     * 用户账户基本信息
     */
    private int balance;	//帐户余额(u财)，单位分

    private int subsidyAccount;	//补助账户

    private int businessAccount;	//业务账户(M卡)

    private int accountStatus;	//账户状态 冻结/启用

    private int frozenAmount;//冻结金额

    private int mepPoint;	//mep积分

    private int mepExperience;	//mep经验

    private int sspPoint;	//ssp积分

    private int sspExperience;	//ssp经验

    private int cipsPoint;		//cips积分

    private int cipsExperience;	//cips经验

    private int ipspPoint;	//ipsp积分

    private int ipspExperience;	//ipsp经验

    private String cashPassword;	//现金账户密码

    private String encryCashPassword;	//现金账户密码(加密)

    private int spendAmount;	//已消费金额

    private String loginName;	//登录名
    private String mobile; 	//手机号码
    private String smsCode;		//验证码
    private String acPassword;	//明文密码
    private String nickName;	//昵称
    private String realName;	//真实姓名
    private String email;	//邮箱
    private String address;	//地址
    private String referrer;	//推荐人
    private String regCity;	//注册城市(中文)
    private String regCityNo;	//注册城市(编码)
    private String wxOpenId;
    private String headImgUrl;// 用户头像（主要是微信）

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getIdcardPicFront() {
        return idcardPicFront;
    }

    public void setIdcardPicFront(String idcardPicFront) {
        this.idcardPicFront = idcardPicFront;
    }

    public String getIdcardPicVerso() {
        return idcardPicVerso;
    }

    public void setIdcardPicVerso(String idcardPicVerso) {
        this.idcardPicVerso = idcardPicVerso;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getAvatarPic() {
        return avatarPic;
    }

    public void setAvatarPic(String avatarPic) {
        this.avatarPic = avatarPic;
    }

    public String getTpWxOpenid() {
        return tpWxOpenid;
    }

    public void setTpWxOpenid(String tpWxOpenid) {
        this.tpWxOpenid = tpWxOpenid;
    }

    public String getTpWxAvatar() {
        return tpWxAvatar;
    }

    public void setTpWxAvatar(String tpWxAvatar) {
        this.tpWxAvatar = tpWxAvatar;
    }

    public String getTpQqOpenid() {
        return tpQqOpenid;
    }

    public void setTpQqOpenid(String tpQqOpenid) {
        this.tpQqOpenid = tpQqOpenid;
    }

    public String getTpQqAvatar() {
        return tpQqAvatar;
    }

    public void setTpQqAvatar(String tpQqAvatar) {
        this.tpQqAvatar = tpQqAvatar;
    }

    public String getTpZfbOpenid() {
        return tpZfbOpenid;
    }

    public void setTpZfbOpenid(String tpZfbOpenid) {
        this.tpZfbOpenid = tpZfbOpenid;
    }

    public String getTpZfbAvatar() {
        return tpZfbAvatar;
    }

    public void setTpZfbAvatar(String tpZfbAvatar) {
        this.tpZfbAvatar = tpZfbAvatar;
    }

    public String getWxOpenid() {
        return wxOpenid;
    }

    public void setWxOpenid(String wxOpenid) {
        this.wxOpenid = wxOpenid;
    }

    public String getWxNickname() {
        return wxNickname;
    }

    public void setWxNickname(String wxNickname) {
        this.wxNickname = wxNickname;
    }

    public int getRealNameStatus() {
        return realNameStatus;
    }

    public void setRealNameStatus(int realNameStatus) {
        this.realNameStatus = realNameStatus;
    }

    public String getRealNameNopassReason() {
        return realNameNopassReason;
    }

    public void setRealNameNopassReason(String realNameNopassReason) {
        this.realNameNopassReason = realNameNopassReason;
    }

    public long getRealNameCommitTime() {
        return realNameCommitTime;
    }

    public void setRealNameCommitTime(long realNameCommitTime) {
        this.realNameCommitTime = realNameCommitTime;
    }

    public long getRealNameAuditTime() {
        return realNameAuditTime;
    }

    public void setRealNameAuditTime(long realNameAuditTime) {
        this.realNameAuditTime = realNameAuditTime;
    }

    public String getCarManagerBg() {
        return carManagerBg;
    }

    public void setCarManagerBg(String carManagerBg) {
        this.carManagerBg = carManagerBg;
    }

    public int getProjectRole() {
        return projectRole;
    }

    public void setProjectRole(int projectRole) {
        this.projectRole = projectRole;
    }

    public int getDetailRole() {
        return detailRole;
    }

    public void setDetailRole(int detailRole) {
        this.detailRole = detailRole;
    }

    public String getMepMembership() {
        return mepMembership;
    }

    public void setMepMembership(String mepMembership) {
        this.mepMembership = mepMembership;
    }

    public long getMepLastLoginTime() {
        return mepLastLoginTime;
    }

    public void setMepLastLoginTime(long mepLastLoginTime) {
        this.mepLastLoginTime = mepLastLoginTime;
    }

    public String getSspMembership() {
        return sspMembership;
    }

    public void setSspMembership(String sspMembership) {
        this.sspMembership = sspMembership;
    }

    public long getSspLastLoginTime() {
        return sspLastLoginTime;
    }

    public void setSspLastLoginTime(long sspLastLoginTime) {
        this.sspLastLoginTime = sspLastLoginTime;
    }

    public String getCipsMembership() {
        return cipsMembership;
    }

    public void setCipsMembership(String cipsMembership) {
        this.cipsMembership = cipsMembership;
    }

    public long getCipsLastLoginTime() {
        return cipsLastLoginTime;
    }

    public void setCipsLastLoginTime(long cipsLastLoginTime) {
        this.cipsLastLoginTime = cipsLastLoginTime;
    }

    public String getIpspMembership() {
        return ipspMembership;
    }

    public void setIpspMembership(String ipspMembership) {
        this.ipspMembership = ipspMembership;
    }

    public long getIpspLastLoginTime() {
        return ipspLastLoginTime;
    }

    public void setIpspLastLoginTime(long ipspLastLoginTime) {
        this.ipspLastLoginTime = ipspLastLoginTime;
    }

    public int getCashierType() {
        return cashierType;
    }

    public void setCashierType(int cashierType) {
        this.cashierType = cashierType;
    }

    public int getReferrerAwardStatus() {
        return referrerAwardStatus;
    }

    public void setReferrerAwardStatus(int referrerAwardStatus) {
        this.referrerAwardStatus = referrerAwardStatus;
    }

    public int getDetailRoleApplyStatus() {
        return detailRoleApplyStatus;
    }

    public void setDetailRoleApplyStatus(int detailRoleApplyStatus) {
        this.detailRoleApplyStatus = detailRoleApplyStatus;
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getSubsidyAccount() {
        return subsidyAccount;
    }

    public void setSubsidyAccount(int subsidyAccount) {
        this.subsidyAccount = subsidyAccount;
    }

    public int getBusinessAccount() {
        return businessAccount;
    }

    public void setBusinessAccount(int businessAccount) {
        this.businessAccount = businessAccount;
    }

    public int getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(int accountStatus) {
        this.accountStatus = accountStatus;
    }

    public int getFrozenAmount() {
        return frozenAmount;
    }

    public void setFrozenAmount(int frozenAmount) {
        this.frozenAmount = frozenAmount;
    }

    public int getMepPoint() {
        return mepPoint;
    }

    public void setMepPoint(int mepPoint) {
        this.mepPoint = mepPoint;
    }

    public int getMepExperience() {
        return mepExperience;
    }

    public void setMepExperience(int mepExperience) {
        this.mepExperience = mepExperience;
    }

    public int getSspPoint() {
        return sspPoint;
    }

    public void setSspPoint(int sspPoint) {
        this.sspPoint = sspPoint;
    }

    public int getSspExperience() {
        return sspExperience;
    }

    public void setSspExperience(int sspExperience) {
        this.sspExperience = sspExperience;
    }

    public int getCipsPoint() {
        return cipsPoint;
    }

    public void setCipsPoint(int cipsPoint) {
        this.cipsPoint = cipsPoint;
    }

    public int getCipsExperience() {
        return cipsExperience;
    }

    public void setCipsExperience(int cipsExperience) {
        this.cipsExperience = cipsExperience;
    }

    public int getIpspPoint() {
        return ipspPoint;
    }

    public void setIpspPoint(int ipspPoint) {
        this.ipspPoint = ipspPoint;
    }

    public int getIpspExperience() {
        return ipspExperience;
    }

    public void setIpspExperience(int ipspExperience) {
        this.ipspExperience = ipspExperience;
    }

    public String getCashPassword() {
        return cashPassword;
    }

    public void setCashPassword(String cashPassword) {
        this.cashPassword = cashPassword;
    }

    public String getEncryCashPassword() {
        return encryCashPassword;
    }

    public void setEncryCashPassword(String encryCashPassword) {
        this.encryCashPassword = encryCashPassword;
    }

    public int getSpendAmount() {
        return spendAmount;
    }

    public void setSpendAmount(int spendAmount) {
        this.spendAmount = spendAmount;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public String getAcPassword() {
        return acPassword;
    }

    public void setAcPassword(String acPassword) {
        this.acPassword = acPassword;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getReferrer() {
        return referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    public String getRegCity() {
        return regCity;
    }

    public void setRegCity(String regCity) {
        this.regCity = regCity;
    }

    public String getRegCityNo() {
        return regCityNo;
    }

    public void setRegCityNo(String regCityNo) {
        this.regCityNo = regCityNo;
    }

    public String getWxOpenId() {
        return wxOpenId;
    }

    public void setWxOpenId(String wxOpenId) {
        this.wxOpenId = wxOpenId;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

}
