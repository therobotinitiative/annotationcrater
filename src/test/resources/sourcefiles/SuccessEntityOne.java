//package com.orbital3d.test.entity.success;

import com.orbital3d.domain.annotation.DomainEntityId;
import com.orbital3d.domain.annotation.DomainEntityMarker;
import com.orbital3d.domain.entity.DomainEntity;

@DomainEntityMarker
public class SuccessEntityOne implements DomainEntity<Long> {
  @DomainEntityId private Long id;

  @Override
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
