package ltsiint.domain;

import ltsiint.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "manufacturingProcesses",
    path = "manufacturingProcesses"
)
public interface ManufacturingProcessRepository
    extends PagingAndSortingRepository<ManufacturingProcess, String> {}
