package com.dht.pojo;

import java.util.ArrayList;
import java.util.List;

public class RolePermVOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RolePermVOExample() {
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

        public Criteria andRpidIsNull() {
            addCriterion("rpid is null");
            return (Criteria) this;
        }

        public Criteria andRpidIsNotNull() {
            addCriterion("rpid is not null");
            return (Criteria) this;
        }

        public Criteria andRpidEqualTo(Integer value) {
            addCriterion("rpid =", value, "rpid");
            return (Criteria) this;
        }

        public Criteria andRpidNotEqualTo(Integer value) {
            addCriterion("rpid <>", value, "rpid");
            return (Criteria) this;
        }

        public Criteria andRpidGreaterThan(Integer value) {
            addCriterion("rpid >", value, "rpid");
            return (Criteria) this;
        }

        public Criteria andRpidGreaterThanOrEqualTo(Integer value) {
            addCriterion("rpid >=", value, "rpid");
            return (Criteria) this;
        }

        public Criteria andRpidLessThan(Integer value) {
            addCriterion("rpid <", value, "rpid");
            return (Criteria) this;
        }

        public Criteria andRpidLessThanOrEqualTo(Integer value) {
            addCriterion("rpid <=", value, "rpid");
            return (Criteria) this;
        }

        public Criteria andRpidIn(List<Integer> values) {
            addCriterion("rpid in", values, "rpid");
            return (Criteria) this;
        }

        public Criteria andRpidNotIn(List<Integer> values) {
            addCriterion("rpid not in", values, "rpid");
            return (Criteria) this;
        }

        public Criteria andRpidBetween(Integer value1, Integer value2) {
            addCriterion("rpid between", value1, value2, "rpid");
            return (Criteria) this;
        }

        public Criteria andRpidNotBetween(Integer value1, Integer value2) {
            addCriterion("rpid not between", value1, value2, "rpid");
            return (Criteria) this;
        }

        public Criteria andRpridIsNull() {
            addCriterion("rprid is null");
            return (Criteria) this;
        }

        public Criteria andRpridIsNotNull() {
            addCriterion("rprid is not null");
            return (Criteria) this;
        }

        public Criteria andRpridEqualTo(Integer value) {
            addCriterion("rprid =", value, "rprid");
            return (Criteria) this;
        }

        public Criteria andRpridNotEqualTo(Integer value) {
            addCriterion("rprid <>", value, "rprid");
            return (Criteria) this;
        }

        public Criteria andRpridGreaterThan(Integer value) {
            addCriterion("rprid >", value, "rprid");
            return (Criteria) this;
        }

        public Criteria andRpridGreaterThanOrEqualTo(Integer value) {
            addCriterion("rprid >=", value, "rprid");
            return (Criteria) this;
        }

        public Criteria andRpridLessThan(Integer value) {
            addCriterion("rprid <", value, "rprid");
            return (Criteria) this;
        }

        public Criteria andRpridLessThanOrEqualTo(Integer value) {
            addCriterion("rprid <=", value, "rprid");
            return (Criteria) this;
        }

        public Criteria andRpridIn(List<Integer> values) {
            addCriterion("rprid in", values, "rprid");
            return (Criteria) this;
        }

        public Criteria andRpridNotIn(List<Integer> values) {
            addCriterion("rprid not in", values, "rprid");
            return (Criteria) this;
        }

        public Criteria andRpridBetween(Integer value1, Integer value2) {
            addCriterion("rprid between", value1, value2, "rprid");
            return (Criteria) this;
        }

        public Criteria andRpridNotBetween(Integer value1, Integer value2) {
            addCriterion("rprid not between", value1, value2, "rprid");
            return (Criteria) this;
        }

        public Criteria andRppidIsNull() {
            addCriterion("rppid is null");
            return (Criteria) this;
        }

        public Criteria andRppidIsNotNull() {
            addCriterion("rppid is not null");
            return (Criteria) this;
        }

        public Criteria andRppidEqualTo(Integer value) {
            addCriterion("rppid =", value, "rppid");
            return (Criteria) this;
        }

        public Criteria andRppidNotEqualTo(Integer value) {
            addCriterion("rppid <>", value, "rppid");
            return (Criteria) this;
        }

        public Criteria andRppidGreaterThan(Integer value) {
            addCriterion("rppid >", value, "rppid");
            return (Criteria) this;
        }

        public Criteria andRppidGreaterThanOrEqualTo(Integer value) {
            addCriterion("rppid >=", value, "rppid");
            return (Criteria) this;
        }

        public Criteria andRppidLessThan(Integer value) {
            addCriterion("rppid <", value, "rppid");
            return (Criteria) this;
        }

        public Criteria andRppidLessThanOrEqualTo(Integer value) {
            addCriterion("rppid <=", value, "rppid");
            return (Criteria) this;
        }

        public Criteria andRppidIn(List<Integer> values) {
            addCriterion("rppid in", values, "rppid");
            return (Criteria) this;
        }

        public Criteria andRppidNotIn(List<Integer> values) {
            addCriterion("rppid not in", values, "rppid");
            return (Criteria) this;
        }

        public Criteria andRppidBetween(Integer value1, Integer value2) {
            addCriterion("rppid between", value1, value2, "rppid");
            return (Criteria) this;
        }

        public Criteria andRppidNotBetween(Integer value1, Integer value2) {
            addCriterion("rppid not between", value1, value2, "rppid");
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