package org.spirit.assembled.transaction.tcc;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import org.spirit.assembled.transaction.api.utils.DateUtils;
import org.spirit.assembled.transaction.tcc.role.Participant;

/**
 * @description 事务
 * @author qiudequan
 * @createTime 2017年1月10日 下午5:10:46 
 */
public class Transaction implements Serializable, TransactionApi {

  private static final long serialVersionUID = -131258096445893759L;
  private TransactionXid xid;
  private TransactionStatus status;
  private TransactionType type;
  private Date createTime;
  private Date updateTime;
  private AtomicInteger retryCount = new AtomicInteger(0);
  private Long version = 1L;
  // 事务参与者
  private List<Participant> participants = new ArrayList<>();

  public Transaction() {
    // 无参构造
  }

  public Transaction(TransactionType type) {
    this.xid = new TransactionXid();
    this.type = type;
    this.status = TransactionStatus.TRY;
  }

  public Transaction(TransactionContext context) {
    this.xid = context.getXid();
    this.type = TransactionType.BRANCH;
    this.status = TransactionStatus.TRY;
  }

  public void addParticipant(Participant participant) {
    this.participants.add(participant);
  }

  public void addParticipants(List<Participant> participants) {
    this.participants.addAll(participants);
  }

  public void preUpdate() {
    this.updateTime = DateUtils.getCurrentDate();
    this.version++;
  }

  public TransactionXid getXid() {
    return xid;
  }

  public void setXid(TransactionXid xid) {
    this.xid = xid;
  }

  public TransactionStatus getStatus() {
    return status;
  }

  public void setStatus(TransactionStatus status) {
    this.status = status;
  }

  public TransactionType getType() {
    return type;
  }

  public void setType(TransactionType type) {
    this.type = type;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public Date getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }

  public Long getVersion() {
    return version;
  }

  public void setVersion(Long version) {
    this.version = version;
  }

  // 获取参与者列表(不可修改)
  public List<Participant> getParticipants() {
    return Collections.unmodifiableList(participants);
  }

  public void setParticipants(List<Participant> participants) {
    this.participants = participants;
  }

  public int getRetryCount() {
    return retryCount.get();
  }

  public void setRetryCount(int retryCount) {
    this.retryCount.set(retryCount);
  }

  public int plusRetryCount() {
    return this.retryCount.incrementAndGet();
  }

  @Override
  public void commit() {
    for (Participant participant : participants) {
      participant.commit();
    }
  }

  @Override
  public void rollback() {
    for (Participant participant : participants) {
      participant.rollback();
    }
  }

}
