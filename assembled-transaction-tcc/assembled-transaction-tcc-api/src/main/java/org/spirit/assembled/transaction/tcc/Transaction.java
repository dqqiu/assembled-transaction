package org.spirit.assembled.transaction.tcc;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.transaction.xa.Xid;

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
  private String id;
  private TransactionXid xid;
  private TransactionStatus transactionStatus;
  private String createBy;
  private Date createTime;
  private String updateBy;
  private Date updateTime;
  private AtomicInteger retryTime = new AtomicInteger(0);
  private Long version = 1L;
  // 事务参与者
  private List<Participant> participants = new ArrayList<>();
  private TransactionType transactionType;

  public Transaction() {
    // 无参构造
    this.xid = new TransactionXid();
  }

  public Transaction(TransactionType transactionType) {
    this.xid = new TransactionXid();
    this.transactionType = transactionType;
    this.transactionStatus = TransactionStatus.TRY;
  }

  public Transaction(TransactionContext context) {
    this.xid = context.getXid();
    this.transactionType = TransactionType.BRANCH;
    this.transactionStatus = TransactionStatus.TRY;
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

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Xid getXid() {
    return xid.clone();
  }

  public void setTransactionXid(TransactionXid xid) {
    this.xid = xid;
  }

  public TransactionXid getTransactionXid() {
    return this.xid;
  }
  
  public TransactionStatus getTransactionStatus() {
    return transactionStatus;
  }

  public void setTransactionStatus(TransactionStatus transactionStatus) {
    this.transactionStatus = transactionStatus;
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

  public String getCreateBy() {
    return createBy;
  }

  public void setCreateBy(String createBy) {
    this.createBy = createBy;
  }

  public String getUpdateBy() {
    return updateBy;
  }

  public void setUpdateBy(String updateBy) {
    this.updateBy = updateBy;
  }

  // 获取参与者列表(不可修改)
  public List<Participant> getParticipants() {
    return Collections.unmodifiableList(participants);
  }

  public void setParticipants(List<Participant> participants) {
    this.participants = participants;
  }

  public int getRetryTime() {
    return retryTime.get();
  }

  public void setRetryTime(int retryCount) {
    this.retryTime.set(retryCount);
  }

  public int plusRetryTime() {
    return this.retryTime.incrementAndGet();
  }

  public TransactionType getTransactionType() {
    return transactionType;
  }

  public void setTransactionType(TransactionType transactionType) {
    this.transactionType = transactionType;
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
