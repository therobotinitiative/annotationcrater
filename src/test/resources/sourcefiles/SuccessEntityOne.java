package sourcefiles;

import org.codebreaker.domain.annotation.DomainEntityId;
import org.codebreaker.domain.annotation.DomainEntityMarker;
import org.codebreaker.domain.entity.DomainEntity;

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
