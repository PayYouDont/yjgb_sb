package com.gospell.chitong.rdcenter.broadcast.broadcastMange.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmergencyinfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public EmergencyinfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getOffset() {
        return offset;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andEmergencynameIsNull() {
            addCriterion("emergencyName is null");
            return (Criteria) this;
        }

        public Criteria andEmergencynameIsNotNull() {
            addCriterion("emergencyName is not null");
            return (Criteria) this;
        }

        public Criteria andEmergencynameEqualTo(String value) {
            addCriterion("emergencyName =", value, "emergencyname");
            return (Criteria) this;
        }

        public Criteria andEmergencynameNotEqualTo(String value) {
            addCriterion("emergencyName <>", value, "emergencyname");
            return (Criteria) this;
        }

        public Criteria andEmergencynameGreaterThan(String value) {
            addCriterion("emergencyName >", value, "emergencyname");
            return (Criteria) this;
        }

        public Criteria andEmergencynameGreaterThanOrEqualTo(String value) {
            addCriterion("emergencyName >=", value, "emergencyname");
            return (Criteria) this;
        }

        public Criteria andEmergencynameLessThan(String value) {
            addCriterion("emergencyName <", value, "emergencyname");
            return (Criteria) this;
        }

        public Criteria andEmergencynameLessThanOrEqualTo(String value) {
            addCriterion("emergencyName <=", value, "emergencyname");
            return (Criteria) this;
        }

        public Criteria andEmergencynameLike(String value) {
            addCriterion("emergencyName like", value, "emergencyname");
            return (Criteria) this;
        }

        public Criteria andEmergencynameNotLike(String value) {
            addCriterion("emergencyName not like", value, "emergencyname");
            return (Criteria) this;
        }

        public Criteria andEmergencynameIn(List<String> values) {
            addCriterion("emergencyName in", values, "emergencyname");
            return (Criteria) this;
        }

        public Criteria andEmergencynameNotIn(List<String> values) {
            addCriterion("emergencyName not in", values, "emergencyname");
            return (Criteria) this;
        }

        public Criteria andEmergencynameBetween(String value1, String value2) {
            addCriterion("emergencyName between", value1, value2, "emergencyname");
            return (Criteria) this;
        }

        public Criteria andEmergencynameNotBetween(String value1, String value2) {
            addCriterion("emergencyName not between", value1, value2, "emergencyname");
            return (Criteria) this;
        }

        public Criteria andEmergencycodeIsNull() {
            addCriterion("emergencyCode is null");
            return (Criteria) this;
        }

        public Criteria andEmergencycodeIsNotNull() {
            addCriterion("emergencyCode is not null");
            return (Criteria) this;
        }

        public Criteria andEmergencycodeEqualTo(String value) {
            addCriterion("emergencyCode =", value, "emergencycode");
            return (Criteria) this;
        }

        public Criteria andEmergencycodeNotEqualTo(String value) {
            addCriterion("emergencyCode <>", value, "emergencycode");
            return (Criteria) this;
        }

        public Criteria andEmergencycodeGreaterThan(String value) {
            addCriterion("emergencyCode >", value, "emergencycode");
            return (Criteria) this;
        }

        public Criteria andEmergencycodeGreaterThanOrEqualTo(String value) {
            addCriterion("emergencyCode >=", value, "emergencycode");
            return (Criteria) this;
        }

        public Criteria andEmergencycodeLessThan(String value) {
            addCriterion("emergencyCode <", value, "emergencycode");
            return (Criteria) this;
        }

        public Criteria andEmergencycodeLessThanOrEqualTo(String value) {
            addCriterion("emergencyCode <=", value, "emergencycode");
            return (Criteria) this;
        }

        public Criteria andEmergencycodeLike(String value) {
            addCriterion("emergencyCode like", value, "emergencycode");
            return (Criteria) this;
        }

        public Criteria andEmergencycodeNotLike(String value) {
            addCriterion("emergencyCode not like", value, "emergencycode");
            return (Criteria) this;
        }

        public Criteria andEmergencycodeIn(List<String> values) {
            addCriterion("emergencyCode in", values, "emergencycode");
            return (Criteria) this;
        }

        public Criteria andEmergencycodeNotIn(List<String> values) {
            addCriterion("emergencyCode not in", values, "emergencycode");
            return (Criteria) this;
        }

        public Criteria andEmergencycodeBetween(String value1, String value2) {
            addCriterion("emergencyCode between", value1, value2, "emergencycode");
            return (Criteria) this;
        }

        public Criteria andEmergencycodeNotBetween(String value1, String value2) {
            addCriterion("emergencyCode not between", value1, value2, "emergencycode");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNull() {
            addCriterion("start_time is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("start_time is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(Date value) {
            addCriterion("start_time =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(Date value) {
            addCriterion("start_time <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(Date value) {
            addCriterion("start_time >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("start_time >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(Date value) {
            addCriterion("start_time <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("start_time <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<Date> values) {
            addCriterion("start_time in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<Date> values) {
            addCriterion("start_time not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(Date value1, Date value2) {
            addCriterion("start_time between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("start_time not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("end_time is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Date value) {
            addCriterion("end_time =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterion("end_time <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterion("end_time >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("end_time >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterion("end_time <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("end_time <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterion("end_time in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterion("end_time not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterion("end_time between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("end_time not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andSoundIsNull() {
            addCriterion("sound is null");
            return (Criteria) this;
        }

        public Criteria andSoundIsNotNull() {
            addCriterion("sound is not null");
            return (Criteria) this;
        }

        public Criteria andSoundEqualTo(String value) {
            addCriterion("sound =", value, "sound");
            return (Criteria) this;
        }

        public Criteria andSoundNotEqualTo(String value) {
            addCriterion("sound <>", value, "sound");
            return (Criteria) this;
        }

        public Criteria andSoundGreaterThan(String value) {
            addCriterion("sound >", value, "sound");
            return (Criteria) this;
        }

        public Criteria andSoundGreaterThanOrEqualTo(String value) {
            addCriterion("sound >=", value, "sound");
            return (Criteria) this;
        }

        public Criteria andSoundLessThan(String value) {
            addCriterion("sound <", value, "sound");
            return (Criteria) this;
        }

        public Criteria andSoundLessThanOrEqualTo(String value) {
            addCriterion("sound <=", value, "sound");
            return (Criteria) this;
        }

        public Criteria andSoundLike(String value) {
            addCriterion("sound like", value, "sound");
            return (Criteria) this;
        }

        public Criteria andSoundNotLike(String value) {
            addCriterion("sound not like", value, "sound");
            return (Criteria) this;
        }

        public Criteria andSoundIn(List<String> values) {
            addCriterion("sound in", values, "sound");
            return (Criteria) this;
        }

        public Criteria andSoundNotIn(List<String> values) {
            addCriterion("sound not in", values, "sound");
            return (Criteria) this;
        }

        public Criteria andSoundBetween(String value1, String value2) {
            addCriterion("sound between", value1, value2, "sound");
            return (Criteria) this;
        }

        public Criteria andSoundNotBetween(String value1, String value2) {
            addCriterion("sound not between", value1, value2, "sound");
            return (Criteria) this;
        }

        public Criteria andDurationIsNull() {
            addCriterion("duration is null");
            return (Criteria) this;
        }

        public Criteria andDurationIsNotNull() {
            addCriterion("duration is not null");
            return (Criteria) this;
        }

        public Criteria andDurationEqualTo(String value) {
            addCriterion("duration =", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotEqualTo(String value) {
            addCriterion("duration <>", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationGreaterThan(String value) {
            addCriterion("duration >", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationGreaterThanOrEqualTo(String value) {
            addCriterion("duration >=", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationLessThan(String value) {
            addCriterion("duration <", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationLessThanOrEqualTo(String value) {
            addCriterion("duration <=", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationLike(String value) {
            addCriterion("duration like", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotLike(String value) {
            addCriterion("duration not like", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationIn(List<String> values) {
            addCriterion("duration in", values, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotIn(List<String> values) {
            addCriterion("duration not in", values, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationBetween(String value1, String value2) {
            addCriterion("duration between", value1, value2, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotBetween(String value1, String value2) {
            addCriterion("duration not between", value1, value2, "duration");
            return (Criteria) this;
        }

        public Criteria andAddresscodeIsNull() {
            addCriterion("addressCode is null");
            return (Criteria) this;
        }

        public Criteria andAddresscodeIsNotNull() {
            addCriterion("addressCode is not null");
            return (Criteria) this;
        }

        public Criteria andAddresscodeEqualTo(String value) {
            addCriterion("addressCode =", value, "addresscode");
            return (Criteria) this;
        }

        public Criteria andAddresscodeNotEqualTo(String value) {
            addCriterion("addressCode <>", value, "addresscode");
            return (Criteria) this;
        }

        public Criteria andAddresscodeGreaterThan(String value) {
            addCriterion("addressCode >", value, "addresscode");
            return (Criteria) this;
        }

        public Criteria andAddresscodeGreaterThanOrEqualTo(String value) {
            addCriterion("addressCode >=", value, "addresscode");
            return (Criteria) this;
        }

        public Criteria andAddresscodeLessThan(String value) {
            addCriterion("addressCode <", value, "addresscode");
            return (Criteria) this;
        }

        public Criteria andAddresscodeLessThanOrEqualTo(String value) {
            addCriterion("addressCode <=", value, "addresscode");
            return (Criteria) this;
        }

        public Criteria andAddresscodeLike(String value) {
            addCriterion("addressCode like", value, "addresscode");
            return (Criteria) this;
        }

        public Criteria andAddresscodeNotLike(String value) {
            addCriterion("addressCode not like", value, "addresscode");
            return (Criteria) this;
        }

        public Criteria andAddresscodeIn(List<String> values) {
            addCriterion("addressCode in", values, "addresscode");
            return (Criteria) this;
        }

        public Criteria andAddresscodeNotIn(List<String> values) {
            addCriterion("addressCode not in", values, "addresscode");
            return (Criteria) this;
        }

        public Criteria andAddresscodeBetween(String value1, String value2) {
            addCriterion("addressCode between", value1, value2, "addresscode");
            return (Criteria) this;
        }

        public Criteria andAddresscodeNotBetween(String value1, String value2) {
            addCriterion("addressCode not between", value1, value2, "addresscode");
            return (Criteria) this;
        }

        public Criteria andProgramIdIsNull() {
            addCriterion("program_id is null");
            return (Criteria) this;
        }

        public Criteria andProgramIdIsNotNull() {
            addCriterion("program_id is not null");
            return (Criteria) this;
        }

        public Criteria andProgramIdEqualTo(Integer value) {
            addCriterion("program_id =", value, "programId");
            return (Criteria) this;
        }

        public Criteria andProgramIdNotEqualTo(Integer value) {
            addCriterion("program_id <>", value, "programId");
            return (Criteria) this;
        }

        public Criteria andProgramIdGreaterThan(Integer value) {
            addCriterion("program_id >", value, "programId");
            return (Criteria) this;
        }

        public Criteria andProgramIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("program_id >=", value, "programId");
            return (Criteria) this;
        }

        public Criteria andProgramIdLessThan(Integer value) {
            addCriterion("program_id <", value, "programId");
            return (Criteria) this;
        }

        public Criteria andProgramIdLessThanOrEqualTo(Integer value) {
            addCriterion("program_id <=", value, "programId");
            return (Criteria) this;
        }

        public Criteria andProgramIdIn(List<Integer> values) {
            addCriterion("program_id in", values, "programId");
            return (Criteria) this;
        }

        public Criteria andProgramIdNotIn(List<Integer> values) {
            addCriterion("program_id not in", values, "programId");
            return (Criteria) this;
        }

        public Criteria andProgramIdBetween(Integer value1, Integer value2) {
            addCriterion("program_id between", value1, value2, "programId");
            return (Criteria) this;
        }

        public Criteria andProgramIdNotBetween(Integer value1, Integer value2) {
            addCriterion("program_id not between", value1, value2, "programId");
            return (Criteria) this;
        }

        public Criteria andProgramdescriptionIsNull() {
            addCriterion("programDescription is null");
            return (Criteria) this;
        }

        public Criteria andProgramdescriptionIsNotNull() {
            addCriterion("programDescription is not null");
            return (Criteria) this;
        }

        public Criteria andProgramdescriptionEqualTo(String value) {
            addCriterion("programDescription =", value, "programdescription");
            return (Criteria) this;
        }

        public Criteria andProgramdescriptionNotEqualTo(String value) {
            addCriterion("programDescription <>", value, "programdescription");
            return (Criteria) this;
        }

        public Criteria andProgramdescriptionGreaterThan(String value) {
            addCriterion("programDescription >", value, "programdescription");
            return (Criteria) this;
        }

        public Criteria andProgramdescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("programDescription >=", value, "programdescription");
            return (Criteria) this;
        }

        public Criteria andProgramdescriptionLessThan(String value) {
            addCriterion("programDescription <", value, "programdescription");
            return (Criteria) this;
        }

        public Criteria andProgramdescriptionLessThanOrEqualTo(String value) {
            addCriterion("programDescription <=", value, "programdescription");
            return (Criteria) this;
        }

        public Criteria andProgramdescriptionLike(String value) {
            addCriterion("programDescription like", value, "programdescription");
            return (Criteria) this;
        }

        public Criteria andProgramdescriptionNotLike(String value) {
            addCriterion("programDescription not like", value, "programdescription");
            return (Criteria) this;
        }

        public Criteria andProgramdescriptionIn(List<String> values) {
            addCriterion("programDescription in", values, "programdescription");
            return (Criteria) this;
        }

        public Criteria andProgramdescriptionNotIn(List<String> values) {
            addCriterion("programDescription not in", values, "programdescription");
            return (Criteria) this;
        }

        public Criteria andProgramdescriptionBetween(String value1, String value2) {
            addCriterion("programDescription between", value1, value2, "programdescription");
            return (Criteria) this;
        }

        public Criteria andProgramdescriptionNotBetween(String value1, String value2) {
            addCriterion("programDescription not between", value1, value2, "programdescription");
            return (Criteria) this;
        }

        public Criteria andInfosourceIdIsNull() {
            addCriterion("infoSource_id is null");
            return (Criteria) this;
        }

        public Criteria andInfosourceIdIsNotNull() {
            addCriterion("infoSource_id is not null");
            return (Criteria) this;
        }

        public Criteria andInfosourceIdEqualTo(Integer value) {
            addCriterion("infoSource_id =", value, "infosourceId");
            return (Criteria) this;
        }

        public Criteria andInfosourceIdNotEqualTo(Integer value) {
            addCriterion("infoSource_id <>", value, "infosourceId");
            return (Criteria) this;
        }

        public Criteria andInfosourceIdGreaterThan(Integer value) {
            addCriterion("infoSource_id >", value, "infosourceId");
            return (Criteria) this;
        }

        public Criteria andInfosourceIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("infoSource_id >=", value, "infosourceId");
            return (Criteria) this;
        }

        public Criteria andInfosourceIdLessThan(Integer value) {
            addCriterion("infoSource_id <", value, "infosourceId");
            return (Criteria) this;
        }

        public Criteria andInfosourceIdLessThanOrEqualTo(Integer value) {
            addCriterion("infoSource_id <=", value, "infosourceId");
            return (Criteria) this;
        }

        public Criteria andInfosourceIdIn(List<Integer> values) {
            addCriterion("infoSource_id in", values, "infosourceId");
            return (Criteria) this;
        }

        public Criteria andInfosourceIdNotIn(List<Integer> values) {
            addCriterion("infoSource_id not in", values, "infosourceId");
            return (Criteria) this;
        }

        public Criteria andInfosourceIdBetween(Integer value1, Integer value2) {
            addCriterion("infoSource_id between", value1, value2, "infosourceId");
            return (Criteria) this;
        }

        public Criteria andInfosourceIdNotBetween(Integer value1, Integer value2) {
            addCriterion("infoSource_id not between", value1, value2, "infosourceId");
            return (Criteria) this;
        }

        public Criteria andAccidentlevelIdIsNull() {
            addCriterion("accidentLevel_id is null");
            return (Criteria) this;
        }

        public Criteria andAccidentlevelIdIsNotNull() {
            addCriterion("accidentLevel_id is not null");
            return (Criteria) this;
        }

        public Criteria andAccidentlevelIdEqualTo(Integer value) {
            addCriterion("accidentLevel_id =", value, "accidentlevelId");
            return (Criteria) this;
        }

        public Criteria andAccidentlevelIdNotEqualTo(Integer value) {
            addCriterion("accidentLevel_id <>", value, "accidentlevelId");
            return (Criteria) this;
        }

        public Criteria andAccidentlevelIdGreaterThan(Integer value) {
            addCriterion("accidentLevel_id >", value, "accidentlevelId");
            return (Criteria) this;
        }

        public Criteria andAccidentlevelIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("accidentLevel_id >=", value, "accidentlevelId");
            return (Criteria) this;
        }

        public Criteria andAccidentlevelIdLessThan(Integer value) {
            addCriterion("accidentLevel_id <", value, "accidentlevelId");
            return (Criteria) this;
        }

        public Criteria andAccidentlevelIdLessThanOrEqualTo(Integer value) {
            addCriterion("accidentLevel_id <=", value, "accidentlevelId");
            return (Criteria) this;
        }

        public Criteria andAccidentlevelIdIn(List<Integer> values) {
            addCriterion("accidentLevel_id in", values, "accidentlevelId");
            return (Criteria) this;
        }

        public Criteria andAccidentlevelIdNotIn(List<Integer> values) {
            addCriterion("accidentLevel_id not in", values, "accidentlevelId");
            return (Criteria) this;
        }

        public Criteria andAccidentlevelIdBetween(Integer value1, Integer value2) {
            addCriterion("accidentLevel_id between", value1, value2, "accidentlevelId");
            return (Criteria) this;
        }

        public Criteria andAccidentlevelIdNotBetween(Integer value1, Integer value2) {
            addCriterion("accidentLevel_id not between", value1, value2, "accidentlevelId");
            return (Criteria) this;
        }

        public Criteria andAccidenttypeIdIsNull() {
            addCriterion("accidentType_id is null");
            return (Criteria) this;
        }

        public Criteria andAccidenttypeIdIsNotNull() {
            addCriterion("accidentType_id is not null");
            return (Criteria) this;
        }

        public Criteria andAccidenttypeIdEqualTo(Integer value) {
            addCriterion("accidentType_id =", value, "accidenttypeId");
            return (Criteria) this;
        }

        public Criteria andAccidenttypeIdNotEqualTo(Integer value) {
            addCriterion("accidentType_id <>", value, "accidenttypeId");
            return (Criteria) this;
        }

        public Criteria andAccidenttypeIdGreaterThan(Integer value) {
            addCriterion("accidentType_id >", value, "accidenttypeId");
            return (Criteria) this;
        }

        public Criteria andAccidenttypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("accidentType_id >=", value, "accidenttypeId");
            return (Criteria) this;
        }

        public Criteria andAccidenttypeIdLessThan(Integer value) {
            addCriterion("accidentType_id <", value, "accidenttypeId");
            return (Criteria) this;
        }

        public Criteria andAccidenttypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("accidentType_id <=", value, "accidenttypeId");
            return (Criteria) this;
        }

        public Criteria andAccidenttypeIdIn(List<Integer> values) {
            addCriterion("accidentType_id in", values, "accidenttypeId");
            return (Criteria) this;
        }

        public Criteria andAccidenttypeIdNotIn(List<Integer> values) {
            addCriterion("accidentType_id not in", values, "accidenttypeId");
            return (Criteria) this;
        }

        public Criteria andAccidenttypeIdBetween(Integer value1, Integer value2) {
            addCriterion("accidentType_id between", value1, value2, "accidenttypeId");
            return (Criteria) this;
        }

        public Criteria andAccidenttypeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("accidentType_id not between", value1, value2, "accidenttypeId");
            return (Criteria) this;
        }

        public Criteria andDisplaymethodIdIsNull() {
            addCriterion("displayMethod_id is null");
            return (Criteria) this;
        }

        public Criteria andDisplaymethodIdIsNotNull() {
            addCriterion("displayMethod_id is not null");
            return (Criteria) this;
        }

        public Criteria andDisplaymethodIdEqualTo(Integer value) {
            addCriterion("displayMethod_id =", value, "displaymethodId");
            return (Criteria) this;
        }

        public Criteria andDisplaymethodIdNotEqualTo(Integer value) {
            addCriterion("displayMethod_id <>", value, "displaymethodId");
            return (Criteria) this;
        }

        public Criteria andDisplaymethodIdGreaterThan(Integer value) {
            addCriterion("displayMethod_id >", value, "displaymethodId");
            return (Criteria) this;
        }

        public Criteria andDisplaymethodIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("displayMethod_id >=", value, "displaymethodId");
            return (Criteria) this;
        }

        public Criteria andDisplaymethodIdLessThan(Integer value) {
            addCriterion("displayMethod_id <", value, "displaymethodId");
            return (Criteria) this;
        }

        public Criteria andDisplaymethodIdLessThanOrEqualTo(Integer value) {
            addCriterion("displayMethod_id <=", value, "displaymethodId");
            return (Criteria) this;
        }

        public Criteria andDisplaymethodIdIn(List<Integer> values) {
            addCriterion("displayMethod_id in", values, "displaymethodId");
            return (Criteria) this;
        }

        public Criteria andDisplaymethodIdNotIn(List<Integer> values) {
            addCriterion("displayMethod_id not in", values, "displaymethodId");
            return (Criteria) this;
        }

        public Criteria andDisplaymethodIdBetween(Integer value1, Integer value2) {
            addCriterion("displayMethod_id between", value1, value2, "displaymethodId");
            return (Criteria) this;
        }

        public Criteria andDisplaymethodIdNotBetween(Integer value1, Integer value2) {
            addCriterion("displayMethod_id not between", value1, value2, "displaymethodId");
            return (Criteria) this;
        }

        public Criteria andDisplaylanguageIdIsNull() {
            addCriterion("displayLanguage_id is null");
            return (Criteria) this;
        }

        public Criteria andDisplaylanguageIdIsNotNull() {
            addCriterion("displayLanguage_id is not null");
            return (Criteria) this;
        }

        public Criteria andDisplaylanguageIdEqualTo(Integer value) {
            addCriterion("displayLanguage_id =", value, "displaylanguageId");
            return (Criteria) this;
        }

        public Criteria andDisplaylanguageIdNotEqualTo(Integer value) {
            addCriterion("displayLanguage_id <>", value, "displaylanguageId");
            return (Criteria) this;
        }

        public Criteria andDisplaylanguageIdGreaterThan(Integer value) {
            addCriterion("displayLanguage_id >", value, "displaylanguageId");
            return (Criteria) this;
        }

        public Criteria andDisplaylanguageIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("displayLanguage_id >=", value, "displaylanguageId");
            return (Criteria) this;
        }

        public Criteria andDisplaylanguageIdLessThan(Integer value) {
            addCriterion("displayLanguage_id <", value, "displaylanguageId");
            return (Criteria) this;
        }

        public Criteria andDisplaylanguageIdLessThanOrEqualTo(Integer value) {
            addCriterion("displayLanguage_id <=", value, "displaylanguageId");
            return (Criteria) this;
        }

        public Criteria andDisplaylanguageIdIn(List<Integer> values) {
            addCriterion("displayLanguage_id in", values, "displaylanguageId");
            return (Criteria) this;
        }

        public Criteria andDisplaylanguageIdNotIn(List<Integer> values) {
            addCriterion("displayLanguage_id not in", values, "displaylanguageId");
            return (Criteria) this;
        }

        public Criteria andDisplaylanguageIdBetween(Integer value1, Integer value2) {
            addCriterion("displayLanguage_id between", value1, value2, "displaylanguageId");
            return (Criteria) this;
        }

        public Criteria andDisplaylanguageIdNotBetween(Integer value1, Integer value2) {
            addCriterion("displayLanguage_id not between", value1, value2, "displaylanguageId");
            return (Criteria) this;
        }

        public Criteria andEmergencylocationIdIsNull() {
            addCriterion("emergencyLocation_id is null");
            return (Criteria) this;
        }

        public Criteria andEmergencylocationIdIsNotNull() {
            addCriterion("emergencyLocation_id is not null");
            return (Criteria) this;
        }

        public Criteria andEmergencylocationIdEqualTo(Integer value) {
            addCriterion("emergencyLocation_id =", value, "emergencylocationId");
            return (Criteria) this;
        }

        public Criteria andEmergencylocationIdNotEqualTo(Integer value) {
            addCriterion("emergencyLocation_id <>", value, "emergencylocationId");
            return (Criteria) this;
        }

        public Criteria andEmergencylocationIdGreaterThan(Integer value) {
            addCriterion("emergencyLocation_id >", value, "emergencylocationId");
            return (Criteria) this;
        }

        public Criteria andEmergencylocationIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("emergencyLocation_id >=", value, "emergencylocationId");
            return (Criteria) this;
        }

        public Criteria andEmergencylocationIdLessThan(Integer value) {
            addCriterion("emergencyLocation_id <", value, "emergencylocationId");
            return (Criteria) this;
        }

        public Criteria andEmergencylocationIdLessThanOrEqualTo(Integer value) {
            addCriterion("emergencyLocation_id <=", value, "emergencylocationId");
            return (Criteria) this;
        }

        public Criteria andEmergencylocationIdIn(List<Integer> values) {
            addCriterion("emergencyLocation_id in", values, "emergencylocationId");
            return (Criteria) this;
        }

        public Criteria andEmergencylocationIdNotIn(List<Integer> values) {
            addCriterion("emergencyLocation_id not in", values, "emergencylocationId");
            return (Criteria) this;
        }

        public Criteria andEmergencylocationIdBetween(Integer value1, Integer value2) {
            addCriterion("emergencyLocation_id between", value1, value2, "emergencylocationId");
            return (Criteria) this;
        }

        public Criteria andEmergencylocationIdNotBetween(Integer value1, Integer value2) {
            addCriterion("emergencyLocation_id not between", value1, value2, "emergencylocationId");
            return (Criteria) this;
        }

        public Criteria andModifybyIsNull() {
            addCriterion("modifyby is null");
            return (Criteria) this;
        }

        public Criteria andModifybyIsNotNull() {
            addCriterion("modifyby is not null");
            return (Criteria) this;
        }

        public Criteria andModifybyEqualTo(String value) {
            addCriterion("modifyby =", value, "modifyby");
            return (Criteria) this;
        }

        public Criteria andModifybyNotEqualTo(String value) {
            addCriterion("modifyby <>", value, "modifyby");
            return (Criteria) this;
        }

        public Criteria andModifybyGreaterThan(String value) {
            addCriterion("modifyby >", value, "modifyby");
            return (Criteria) this;
        }

        public Criteria andModifybyGreaterThanOrEqualTo(String value) {
            addCriterion("modifyby >=", value, "modifyby");
            return (Criteria) this;
        }

        public Criteria andModifybyLessThan(String value) {
            addCriterion("modifyby <", value, "modifyby");
            return (Criteria) this;
        }

        public Criteria andModifybyLessThanOrEqualTo(String value) {
            addCriterion("modifyby <=", value, "modifyby");
            return (Criteria) this;
        }

        public Criteria andModifybyLike(String value) {
            addCriterion("modifyby like", value, "modifyby");
            return (Criteria) this;
        }

        public Criteria andModifybyNotLike(String value) {
            addCriterion("modifyby not like", value, "modifyby");
            return (Criteria) this;
        }

        public Criteria andModifybyIn(List<String> values) {
            addCriterion("modifyby in", values, "modifyby");
            return (Criteria) this;
        }

        public Criteria andModifybyNotIn(List<String> values) {
            addCriterion("modifyby not in", values, "modifyby");
            return (Criteria) this;
        }

        public Criteria andModifybyBetween(String value1, String value2) {
            addCriterion("modifyby between", value1, value2, "modifyby");
            return (Criteria) this;
        }

        public Criteria andModifybyNotBetween(String value1, String value2) {
            addCriterion("modifyby not between", value1, value2, "modifyby");
            return (Criteria) this;
        }

        public Criteria andModifyIsNull() {
            addCriterion("modify is null");
            return (Criteria) this;
        }

        public Criteria andModifyIsNotNull() {
            addCriterion("modify is not null");
            return (Criteria) this;
        }

        public Criteria andModifyEqualTo(Date value) {
            addCriterion("modify =", value, "modify");
            return (Criteria) this;
        }

        public Criteria andModifyNotEqualTo(Date value) {
            addCriterion("modify <>", value, "modify");
            return (Criteria) this;
        }

        public Criteria andModifyGreaterThan(Date value) {
            addCriterion("modify >", value, "modify");
            return (Criteria) this;
        }

        public Criteria andModifyGreaterThanOrEqualTo(Date value) {
            addCriterion("modify >=", value, "modify");
            return (Criteria) this;
        }

        public Criteria andModifyLessThan(Date value) {
            addCriterion("modify <", value, "modify");
            return (Criteria) this;
        }

        public Criteria andModifyLessThanOrEqualTo(Date value) {
            addCriterion("modify <=", value, "modify");
            return (Criteria) this;
        }

        public Criteria andModifyIn(List<Date> values) {
            addCriterion("modify in", values, "modify");
            return (Criteria) this;
        }

        public Criteria andModifyNotIn(List<Date> values) {
            addCriterion("modify not in", values, "modify");
            return (Criteria) this;
        }

        public Criteria andModifyBetween(Date value1, Date value2) {
            addCriterion("modify between", value1, value2, "modify");
            return (Criteria) this;
        }

        public Criteria andModifyNotBetween(Date value1, Date value2) {
            addCriterion("modify not between", value1, value2, "modify");
            return (Criteria) this;
        }

        public Criteria andCreatebyIsNull() {
            addCriterion("createby is null");
            return (Criteria) this;
        }

        public Criteria andCreatebyIsNotNull() {
            addCriterion("createby is not null");
            return (Criteria) this;
        }

        public Criteria andCreatebyEqualTo(String value) {
            addCriterion("createby =", value, "createby");
            return (Criteria) this;
        }

        public Criteria andCreatebyNotEqualTo(String value) {
            addCriterion("createby <>", value, "createby");
            return (Criteria) this;
        }

        public Criteria andCreatebyGreaterThan(String value) {
            addCriterion("createby >", value, "createby");
            return (Criteria) this;
        }

        public Criteria andCreatebyGreaterThanOrEqualTo(String value) {
            addCriterion("createby >=", value, "createby");
            return (Criteria) this;
        }

        public Criteria andCreatebyLessThan(String value) {
            addCriterion("createby <", value, "createby");
            return (Criteria) this;
        }

        public Criteria andCreatebyLessThanOrEqualTo(String value) {
            addCriterion("createby <=", value, "createby");
            return (Criteria) this;
        }

        public Criteria andCreatebyLike(String value) {
            addCriterion("createby like", value, "createby");
            return (Criteria) this;
        }

        public Criteria andCreatebyNotLike(String value) {
            addCriterion("createby not like", value, "createby");
            return (Criteria) this;
        }

        public Criteria andCreatebyIn(List<String> values) {
            addCriterion("createby in", values, "createby");
            return (Criteria) this;
        }

        public Criteria andCreatebyNotIn(List<String> values) {
            addCriterion("createby not in", values, "createby");
            return (Criteria) this;
        }

        public Criteria andCreatebyBetween(String value1, String value2) {
            addCriterion("createby between", value1, value2, "createby");
            return (Criteria) this;
        }

        public Criteria andCreatebyNotBetween(String value1, String value2) {
            addCriterion("createby not between", value1, value2, "createby");
            return (Criteria) this;
        }

        public Criteria andCreatedateIsNull() {
            addCriterion("createdate is null");
            return (Criteria) this;
        }

        public Criteria andCreatedateIsNotNull() {
            addCriterion("createdate is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedateEqualTo(Date value) {
            addCriterion("createdate =", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateNotEqualTo(Date value) {
            addCriterion("createdate <>", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateGreaterThan(Date value) {
            addCriterion("createdate >", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateGreaterThanOrEqualTo(Date value) {
            addCriterion("createdate >=", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateLessThan(Date value) {
            addCriterion("createdate <", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateLessThanOrEqualTo(Date value) {
            addCriterion("createdate <=", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateIn(List<Date> values) {
            addCriterion("createdate in", values, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateNotIn(List<Date> values) {
            addCriterion("createdate not in", values, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateBetween(Date value1, Date value2) {
            addCriterion("createdate between", value1, value2, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateNotBetween(Date value1, Date value2) {
            addCriterion("createdate not between", value1, value2, "createdate");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andUnitnameIsNull() {
            addCriterion("unitName is null");
            return (Criteria) this;
        }

        public Criteria andUnitnameIsNotNull() {
            addCriterion("unitName is not null");
            return (Criteria) this;
        }

        public Criteria andUnitnameEqualTo(String value) {
            addCriterion("unitName =", value, "unitname");
            return (Criteria) this;
        }

        public Criteria andUnitnameNotEqualTo(String value) {
            addCriterion("unitName <>", value, "unitname");
            return (Criteria) this;
        }

        public Criteria andUnitnameGreaterThan(String value) {
            addCriterion("unitName >", value, "unitname");
            return (Criteria) this;
        }

        public Criteria andUnitnameGreaterThanOrEqualTo(String value) {
            addCriterion("unitName >=", value, "unitname");
            return (Criteria) this;
        }

        public Criteria andUnitnameLessThan(String value) {
            addCriterion("unitName <", value, "unitname");
            return (Criteria) this;
        }

        public Criteria andUnitnameLessThanOrEqualTo(String value) {
            addCriterion("unitName <=", value, "unitname");
            return (Criteria) this;
        }

        public Criteria andUnitnameLike(String value) {
            addCriterion("unitName like", value, "unitname");
            return (Criteria) this;
        }

        public Criteria andUnitnameNotLike(String value) {
            addCriterion("unitName not like", value, "unitname");
            return (Criteria) this;
        }

        public Criteria andUnitnameIn(List<String> values) {
            addCriterion("unitName in", values, "unitname");
            return (Criteria) this;
        }

        public Criteria andUnitnameNotIn(List<String> values) {
            addCriterion("unitName not in", values, "unitname");
            return (Criteria) this;
        }

        public Criteria andUnitnameBetween(String value1, String value2) {
            addCriterion("unitName between", value1, value2, "unitname");
            return (Criteria) this;
        }

        public Criteria andUnitnameNotBetween(String value1, String value2) {
            addCriterion("unitName not between", value1, value2, "unitname");
            return (Criteria) this;
        }

        public Criteria andEbmIdIsNull() {
            addCriterion("EBM_ID is null");
            return (Criteria) this;
        }

        public Criteria andEbmIdIsNotNull() {
            addCriterion("EBM_ID is not null");
            return (Criteria) this;
        }

        public Criteria andEbmIdEqualTo(Integer value) {
            addCriterion("EBM_ID =", value, "ebmId");
            return (Criteria) this;
        }

        public Criteria andEbmIdNotEqualTo(Integer value) {
            addCriterion("EBM_ID <>", value, "ebmId");
            return (Criteria) this;
        }

        public Criteria andEbmIdGreaterThan(Integer value) {
            addCriterion("EBM_ID >", value, "ebmId");
            return (Criteria) this;
        }

        public Criteria andEbmIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("EBM_ID >=", value, "ebmId");
            return (Criteria) this;
        }

        public Criteria andEbmIdLessThan(Integer value) {
            addCriterion("EBM_ID <", value, "ebmId");
            return (Criteria) this;
        }

        public Criteria andEbmIdLessThanOrEqualTo(Integer value) {
            addCriterion("EBM_ID <=", value, "ebmId");
            return (Criteria) this;
        }

        public Criteria andEbmIdIn(List<Integer> values) {
            addCriterion("EBM_ID in", values, "ebmId");
            return (Criteria) this;
        }

        public Criteria andEbmIdNotIn(List<Integer> values) {
            addCriterion("EBM_ID not in", values, "ebmId");
            return (Criteria) this;
        }

        public Criteria andEbmIdBetween(Integer value1, Integer value2) {
            addCriterion("EBM_ID between", value1, value2, "ebmId");
            return (Criteria) this;
        }

        public Criteria andEbmIdNotBetween(Integer value1, Integer value2) {
            addCriterion("EBM_ID not between", value1, value2, "ebmId");
            return (Criteria) this;
        }

        public Criteria andAreacodeIsNull() {
            addCriterion("areaCode is null");
            return (Criteria) this;
        }

        public Criteria andAreacodeIsNotNull() {
            addCriterion("areaCode is not null");
            return (Criteria) this;
        }

        public Criteria andAreacodeEqualTo(String value) {
            addCriterion("areaCode =", value, "areacode");
            return (Criteria) this;
        }

        public Criteria andAreacodeNotEqualTo(String value) {
            addCriterion("areaCode <>", value, "areacode");
            return (Criteria) this;
        }

        public Criteria andAreacodeGreaterThan(String value) {
            addCriterion("areaCode >", value, "areacode");
            return (Criteria) this;
        }

        public Criteria andAreacodeGreaterThanOrEqualTo(String value) {
            addCriterion("areaCode >=", value, "areacode");
            return (Criteria) this;
        }

        public Criteria andAreacodeLessThan(String value) {
            addCriterion("areaCode <", value, "areacode");
            return (Criteria) this;
        }

        public Criteria andAreacodeLessThanOrEqualTo(String value) {
            addCriterion("areaCode <=", value, "areacode");
            return (Criteria) this;
        }

        public Criteria andAreacodeLike(String value) {
            addCriterion("areaCode like", value, "areacode");
            return (Criteria) this;
        }

        public Criteria andAreacodeNotLike(String value) {
            addCriterion("areaCode not like", value, "areacode");
            return (Criteria) this;
        }

        public Criteria andAreacodeIn(List<String> values) {
            addCriterion("areaCode in", values, "areacode");
            return (Criteria) this;
        }

        public Criteria andAreacodeNotIn(List<String> values) {
            addCriterion("areaCode not in", values, "areacode");
            return (Criteria) this;
        }

        public Criteria andAreacodeBetween(String value1, String value2) {
            addCriterion("areaCode between", value1, value2, "areacode");
            return (Criteria) this;
        }

        public Criteria andAreacodeNotBetween(String value1, String value2) {
            addCriterion("areaCode not between", value1, value2, "areacode");
            return (Criteria) this;
        }

        public Criteria andFlagIsNull() {
            addCriterion("flag is null");
            return (Criteria) this;
        }

        public Criteria andFlagIsNotNull() {
            addCriterion("flag is not null");
            return (Criteria) this;
        }

        public Criteria andFlagEqualTo(String value) {
            addCriterion("flag =", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotEqualTo(String value) {
            addCriterion("flag <>", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThan(String value) {
            addCriterion("flag >", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThanOrEqualTo(String value) {
            addCriterion("flag >=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThan(String value) {
            addCriterion("flag <", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThanOrEqualTo(String value) {
            addCriterion("flag <=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLike(String value) {
            addCriterion("flag like", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotLike(String value) {
            addCriterion("flag not like", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagIn(List<String> values) {
            addCriterion("flag in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotIn(List<String> values) {
            addCriterion("flag not in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagBetween(String value1, String value2) {
            addCriterion("flag between", value1, value2, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotBetween(String value1, String value2) {
            addCriterion("flag not between", value1, value2, "flag");
            return (Criteria) this;
        }

        public Criteria andAddresscodenameIsNull() {
            addCriterion("addressCodeName is null");
            return (Criteria) this;
        }

        public Criteria andAddresscodenameIsNotNull() {
            addCriterion("addressCodeName is not null");
            return (Criteria) this;
        }

        public Criteria andAddresscodenameEqualTo(String value) {
            addCriterion("addressCodeName =", value, "addresscodename");
            return (Criteria) this;
        }

        public Criteria andAddresscodenameNotEqualTo(String value) {
            addCriterion("addressCodeName <>", value, "addresscodename");
            return (Criteria) this;
        }

        public Criteria andAddresscodenameGreaterThan(String value) {
            addCriterion("addressCodeName >", value, "addresscodename");
            return (Criteria) this;
        }

        public Criteria andAddresscodenameGreaterThanOrEqualTo(String value) {
            addCriterion("addressCodeName >=", value, "addresscodename");
            return (Criteria) this;
        }

        public Criteria andAddresscodenameLessThan(String value) {
            addCriterion("addressCodeName <", value, "addresscodename");
            return (Criteria) this;
        }

        public Criteria andAddresscodenameLessThanOrEqualTo(String value) {
            addCriterion("addressCodeName <=", value, "addresscodename");
            return (Criteria) this;
        }

        public Criteria andAddresscodenameLike(String value) {
            addCriterion("addressCodeName like", value, "addresscodename");
            return (Criteria) this;
        }

        public Criteria andAddresscodenameNotLike(String value) {
            addCriterion("addressCodeName not like", value, "addresscodename");
            return (Criteria) this;
        }

        public Criteria andAddresscodenameIn(List<String> values) {
            addCriterion("addressCodeName in", values, "addresscodename");
            return (Criteria) this;
        }

        public Criteria andAddresscodenameNotIn(List<String> values) {
            addCriterion("addressCodeName not in", values, "addresscodename");
            return (Criteria) this;
        }

        public Criteria andAddresscodenameBetween(String value1, String value2) {
            addCriterion("addressCodeName between", value1, value2, "addresscodename");
            return (Criteria) this;
        }

        public Criteria andAddresscodenameNotBetween(String value1, String value2) {
            addCriterion("addressCodeName not between", value1, value2, "addresscodename");
            return (Criteria) this;
        }
    }

    /**
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}