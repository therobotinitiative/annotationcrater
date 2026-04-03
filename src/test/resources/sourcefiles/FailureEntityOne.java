
import com.orbital3d.domain.annotation.DomainEntityMarker;
import com.orbital3d.domain.entity.DomainEntity;

@DomainEntityMarker
public class FailureEntityOne implements DomainEntity<Long> {
    private Long id;
    
    @Override
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
}
