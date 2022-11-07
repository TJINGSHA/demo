package com.dht.pojo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class UserVOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected Integer start;

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    protected Integer limit;

    protected List<Criteria> oredCriteria;

    public UserVOExample() {
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

        public Criteria andUidIsNull() {
            addCriterion("uid is null");
            return (Criteria) this;
        }

        public Criteria andUidIsNotNull() {
            addCriterion("uid is not null");
            return (Criteria) this;
        }

        public Criteria andUidEqualTo(Integer value) {
            addCriterion("uid =", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotEqualTo(Integer value) {
            addCriterion("uid <>", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThan(Integer value) {
            addCriterion("uid >", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThanOrEqualTo(Integer value) {
            addCriterion("uid >=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThan(Integer value) {
            addCriterion("uid <", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThanOrEqualTo(Integer value) {
            addCriterion("uid <=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidIn(List<Integer> values) {
            addCriterion("uid in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotIn(List<Integer> values) {
            addCriterion("uid not in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidBetween(Integer value1, Integer value2) {
            addCriterion("uid between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotBetween(Integer value1, Integer value2) {
            addCriterion("uid not between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUstatusIsNull() {
            addCriterion("ustatus is null");
            return (Criteria) this;
        }

        public Criteria andUstatusIsNotNull() {
            addCriterion("ustatus is not null");
            return (Criteria) this;
        }

        public Criteria andUstatusEqualTo(Integer value) {
            addCriterion("ustatus =", value, "ustatus");
            return (Criteria) this;
        }

        public Criteria andUstatusNotEqualTo(Integer value) {
            addCriterion("ustatus <>", value, "ustatus");
            return (Criteria) this;
        }

        public Criteria andUstatusGreaterThan(Integer value) {
            addCriterion("ustatus >", value, "ustatus");
            return (Criteria) this;
        }

        public Criteria andUstatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("ustatus >=", value, "ustatus");
            return (Criteria) this;
        }

        public Criteria andUstatusLessThan(Integer value) {
            addCriterion("ustatus <", value, "ustatus");
            return (Criteria) this;
        }

        public Criteria andUstatusLessThanOrEqualTo(Integer value) {
            addCriterion("ustatus <=", value, "ustatus");
            return (Criteria) this;
        }

        public Criteria andUstatusIn(List<Integer> values) {
            addCriterion("ustatus in", values, "ustatus");
            return (Criteria) this;
        }

        public Criteria andUstatusNotIn(List<Integer> values) {
            addCriterion("ustatus not in", values, "ustatus");
            return (Criteria) this;
        }

        public Criteria andUstatusBetween(Integer value1, Integer value2) {
            addCriterion("ustatus between", value1, value2, "ustatus");
            return (Criteria) this;
        }

        public Criteria andUstatusNotBetween(Integer value1, Integer value2) {
            addCriterion("ustatus not between", value1, value2, "ustatus");
            return (Criteria) this;
        }

        public Criteria andUloginidIsNull() {
            addCriterion("uloginid is null");
            return (Criteria) this;
        }

        public Criteria andUloginidIsNotNull() {
            addCriterion("uloginid is not null");
            return (Criteria) this;
        }

        public Criteria andUloginidEqualTo(String value) {
            addCriterion("uloginid =", value, "uloginid");
            return (Criteria) this;
        }

        public Criteria andUloginidNotEqualTo(String value) {
            addCriterion("uloginid <>", value, "uloginid");
            return (Criteria) this;
        }

        public Criteria andUloginidGreaterThan(String value) {
            addCriterion("uloginid >", value, "uloginid");
            return (Criteria) this;
        }

        public Criteria andUloginidGreaterThanOrEqualTo(String value) {
            addCriterion("uloginid >=", value, "uloginid");
            return (Criteria) this;
        }

        public Criteria andUloginidLessThan(String value) {
            addCriterion("uloginid <", value, "uloginid");
            return (Criteria) this;
        }

        public Criteria andUloginidLessThanOrEqualTo(String value) {
            addCriterion("uloginid <=", value, "uloginid");
            return (Criteria) this;
        }

        public Criteria andUloginidLike(String value) {
            addCriterion("uloginid like", value, "uloginid");
            return (Criteria) this;
        }

        public Criteria andUloginidNotLike(String value) {
            addCriterion("uloginid not like", value, "uloginid");
            return (Criteria) this;
        }

        public Criteria andUloginidIn(List<String> values) {
            addCriterion("uloginid in", values, "uloginid");
            return (Criteria) this;
        }

        public Criteria andUloginidNotIn(List<String> values) {
            addCriterion("uloginid not in", values, "uloginid");
            return (Criteria) this;
        }

        public Criteria andUloginidBetween(String value1, String value2) {
            addCriterion("uloginid between", value1, value2, "uloginid");
            return (Criteria) this;
        }

        public Criteria andUloginidNotBetween(String value1, String value2) {
            addCriterion("uloginid not between", value1, value2, "uloginid");
            return (Criteria) this;
        }

        public Criteria andUnameIsNull() {
            addCriterion("uname is null");
            return (Criteria) this;
        }

        public Criteria andUnameIsNotNull() {
            addCriterion("uname is not null");
            return (Criteria) this;
        }

        public Criteria andUnameEqualTo(String value) {
            addCriterion("uname =", value, "uname");
            return (Criteria) this;
        }

        public Criteria andUnameNotEqualTo(String value) {
            addCriterion("uname <>", value, "uname");
            return (Criteria) this;
        }

        public Criteria andUnameGreaterThan(String value) {
            addCriterion("uname >", value, "uname");
            return (Criteria) this;
        }

        public Criteria andUnameGreaterThanOrEqualTo(String value) {
            addCriterion("uname >=", value, "uname");
            return (Criteria) this;
        }

        public Criteria andUnameLessThan(String value) {
            addCriterion("uname <", value, "uname");
            return (Criteria) this;
        }

        public Criteria andUnameLessThanOrEqualTo(String value) {
            addCriterion("uname <=", value, "uname");
            return (Criteria) this;
        }

        public Criteria andUnameLike(String value) {
            addCriterion("uname like", value, "uname");
            return (Criteria) this;
        }

        public Criteria andUnameNotLike(String value) {
            addCriterion("uname not like", value, "uname");
            return (Criteria) this;
        }

        public Criteria andUnameIn(List<String> values) {
            addCriterion("uname in", values, "uname");
            return (Criteria) this;
        }

        public Criteria andUnameNotIn(List<String> values) {
            addCriterion("uname not in", values, "uname");
            return (Criteria) this;
        }

        public Criteria andUnameBetween(String value1, String value2) {
            addCriterion("uname between", value1, value2, "uname");
            return (Criteria) this;
        }

        public Criteria andUnameNotBetween(String value1, String value2) {
            addCriterion("uname not between", value1, value2, "uname");
            return (Criteria) this;
        }

        public Criteria andUpwdIsNull() {
            addCriterion("upwd is null");
            return (Criteria) this;
        }

        public Criteria andUpwdIsNotNull() {
            addCriterion("upwd is not null");
            return (Criteria) this;
        }

        public Criteria andUpwdEqualTo(String value) {
            addCriterion("upwd =", value, "upwd");
            return (Criteria) this;
        }

        public Criteria andUpwdNotEqualTo(String value) {
            addCriterion("upwd <>", value, "upwd");
            return (Criteria) this;
        }

        public Criteria andUpwdGreaterThan(String value) {
            addCriterion("upwd >", value, "upwd");
            return (Criteria) this;
        }

        public Criteria andUpwdGreaterThanOrEqualTo(String value) {
            addCriterion("upwd >=", value, "upwd");
            return (Criteria) this;
        }

        public Criteria andUpwdLessThan(String value) {
            addCriterion("upwd <", value, "upwd");
            return (Criteria) this;
        }

        public Criteria andUpwdLessThanOrEqualTo(String value) {
            addCriterion("upwd <=", value, "upwd");
            return (Criteria) this;
        }

        public Criteria andUpwdLike(String value) {
            addCriterion("upwd like", value, "upwd");
            return (Criteria) this;
        }

        public Criteria andUpwdNotLike(String value) {
            addCriterion("upwd not like", value, "upwd");
            return (Criteria) this;
        }

        public Criteria andUpwdIn(List<String> values) {
            addCriterion("upwd in", values, "upwd");
            return (Criteria) this;
        }

        public Criteria andUpwdNotIn(List<String> values) {
            addCriterion("upwd not in", values, "upwd");
            return (Criteria) this;
        }

        public Criteria andUpwdBetween(String value1, String value2) {
            addCriterion("upwd between", value1, value2, "upwd");
            return (Criteria) this;
        }

        public Criteria andUpwdNotBetween(String value1, String value2) {
            addCriterion("upwd not between", value1, value2, "upwd");
            return (Criteria) this;
        }

        public Criteria andUsaltIsNull() {
            addCriterion("usalt is null");
            return (Criteria) this;
        }

        public Criteria andUsaltIsNotNull() {
            addCriterion("usalt is not null");
            return (Criteria) this;
        }

        public Criteria andUsaltEqualTo(String value) {
            addCriterion("usalt =", value, "usalt");
            return (Criteria) this;
        }

        public Criteria andUsaltNotEqualTo(String value) {
            addCriterion("usalt <>", value, "usalt");
            return (Criteria) this;
        }

        public Criteria andUsaltGreaterThan(String value) {
            addCriterion("usalt >", value, "usalt");
            return (Criteria) this;
        }

        public Criteria andUsaltGreaterThanOrEqualTo(String value) {
            addCriterion("usalt >=", value, "usalt");
            return (Criteria) this;
        }

        public Criteria andUsaltLessThan(String value) {
            addCriterion("usalt <", value, "usalt");
            return (Criteria) this;
        }

        public Criteria andUsaltLessThanOrEqualTo(String value) {
            addCriterion("usalt <=", value, "usalt");
            return (Criteria) this;
        }

        public Criteria andUsaltLike(String value) {
            addCriterion("usalt like", value, "usalt");
            return (Criteria) this;
        }

        public Criteria andUsaltNotLike(String value) {
            addCriterion("usalt not like", value, "usalt");
            return (Criteria) this;
        }

        public Criteria andUsaltIn(List<String> values) {
            addCriterion("usalt in", values, "usalt");
            return (Criteria) this;
        }

        public Criteria andUsaltNotIn(List<String> values) {
            addCriterion("usalt not in", values, "usalt");
            return (Criteria) this;
        }

        public Criteria andUsaltBetween(String value1, String value2) {
            addCriterion("usalt between", value1, value2, "usalt");
            return (Criteria) this;
        }

        public Criteria andUsaltNotBetween(String value1, String value2) {
            addCriterion("usalt not between", value1, value2, "usalt");
            return (Criteria) this;
        }

        public Criteria andUlasttimeIsNull() {
            addCriterion("ulasttime is null");
            return (Criteria) this;
        }

        public Criteria andUlasttimeIsNotNull() {
            addCriterion("ulasttime is not null");
            return (Criteria) this;
        }

        public Criteria andUlasttimeEqualTo(Timestamp value) {
            addCriterion("ulasttime =", value, "ulasttime");
            return (Criteria) this;
        }

        public Criteria andUlasttimeNotEqualTo(Timestamp value) {
            addCriterion("ulasttime <>", value, "ulasttime");
            return (Criteria) this;
        }

        public Criteria andUlasttimeGreaterThan(Timestamp value) {
            addCriterion("ulasttime >", value, "ulasttime");
            return (Criteria) this;
        }

        public Criteria andUlasttimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("ulasttime >=", value, "ulasttime");
            return (Criteria) this;
        }

        public Criteria andUlasttimeLessThan(Timestamp value) {
            addCriterion("ulasttime <", value, "ulasttime");
            return (Criteria) this;
        }

        public Criteria andUlasttimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("ulasttime <=", value, "ulasttime");
            return (Criteria) this;
        }

        public Criteria andUlasttimeIn(List<Timestamp> values) {
            addCriterion("ulasttime in", values, "ulasttime");
            return (Criteria) this;
        }

        public Criteria andUlasttimeNotIn(List<Timestamp> values) {
            addCriterion("ulasttime not in", values, "ulasttime");
            return (Criteria) this;
        }

        public Criteria andUlasttimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("ulasttime between", value1, value2, "ulasttime");
            return (Criteria) this;
        }

        public Criteria andUlasttimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("ulasttime not between", value1, value2, "ulasttime");
            return (Criteria) this;
        }
    }

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