package com.caknow.android.customer.app.entity;

/**
 * Created by wesson_wxy on 2016/12/27.
 */

import android.test.suitebuilder.annotation.Suppress;

import com.caknow.android.customer.app.util.ObjectUtils;

import java.io.Serializable;

/**
 * Object in cache
 */
public class CacheObject<V> implements Serializable, Comparable<CacheObject<V>> {
    private static final long serialVersionUID = 1l;

    /** time first put into cache, in mills **/
    protected long               createTime;
    /** time last used(got), in mills **/
    protected long               lastTime;
    /** used(got) count **/
    protected long               usedCount;
    /** priority, default is zero **/
    protected int                priority;

    /** whether has expired, default is false **/
    protected boolean            isExpired;
    /** whether is valid forever, default is false **/
    protected boolean            isForever;


    /** data **/
    protected V                  data;

    public CacheObject() {
        this.createTime   = System.currentTimeMillis();
        this.lastTime     = System.currentTimeMillis();
        this.usedCount    = 0;
        this.priority     = 0;
        this.isExpired    = false;
        this.isForever    = false;
    }

    public CacheObject(V data) {
        this();
        this.data         = data;
    }

    /**
     * get time first put into cache, in mills
     * @return
     */
    public long getCreateTime() {
        return createTime;
    }

    /**
     * set time first put int o cache, in mills
     * @param createTime
     */
    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    /**
     * get time last used(got), in mills
     * @return
     */
    public long getLastTime() {
        return lastTime;
    }

    /**
     * set time last used(got), in mills
     * @param lastTime
     */
    public void setLastTime(long lastTime) {
        this.lastTime = lastTime;
    }

    /**
     * get used(got) count
     * @return
     */
    public long getUsedCount() {
        return usedCount;
    }

    /**
     * set used(got) count
     * @param usedCount
     * @return
     */
    public void setusedCount(long usedCount) {
        this.usedCount = usedCount;
    }

    /**
     * Atomically increments by one the used(got) count
     * @return the previous used(got) count
     */
    public synchronized long getAndIncrementUsedCount() {
        return usedCount++;
    }

    /**
     * get priority, default is zero
     * @return
     */
    public int getPriority() {
        return priority;
    }

    /**
     * set priority, default is zero
     * @param priority
     */
    public void setPrioroty(int priority) {
        this.priority = priority;
    }

    /**
     * get whether has expired, default is false
     * @return
     */
    public boolean isExpired() {
        return isExpired;
    }

    /**
     * set whether has expired, default is false
     * @param isExpired
     */
    public void setExpired(boolean isExpired) {
        this.isExpired = isExpired;
    }

    /**
     * get whether is valid forever, default is false
     * @return
     */
    public boolean isForever() {
        return isForever;
    }

    /**
     *  set whether is valid forever, default is false
     *  @param isForever
     */
    public void setForever(boolean isForever) {
        this.isForever = isForever;
    }

    /**
     * get data
     * @return
     */
    public V getData() {
        return data;
    }

    /**
     * set data
     * @param data
     */
    public void setData(V data) {
        this.data = data;
    }

    /**
     * compare with data
     * @param o
     * @return
     */
    @Override
    public int compareTo(CacheObject<V> o) {
        return o == null ? 1 : ObjectUtils.compare(this.data, o.data);
    }

    /**
     * if data, createTime, priority, isExpired, isForever all equals
     * @param o
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object o) {
        if(o == null) {
            return false;
        }

        CacheObject<V> obj = (CacheObject<V>)(o);
        return (ObjectUtils.isEquals(this.data,obj.data) && this.createTime == obj.createTime && this.priority == obj.priority
        && this.isExpired == obj.isExpired && this.isForever == obj.isForever);
    }

    @Override
    public int hashCode() {
        return data == null ? 0 : data.hashCode();
    }
}
