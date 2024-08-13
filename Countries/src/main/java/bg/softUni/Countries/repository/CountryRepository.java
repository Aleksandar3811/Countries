package bg.softUni.Countries.repository;

import bg.softUni.Countries.entity.CategoryType;
import bg.softUni.Countries.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country,Long> {
    List<Country> findAllByCategories_Name(CategoryType categoryType);
}
