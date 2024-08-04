package pl.coderslab.charity.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CoupleOfInstitutions {

    private Institution institution1;

    private Institution institution2;

    public List<CoupleOfInstitutions> getCoupleOfInstitutionsList(List<Institution> institutions) {
        List<CoupleOfInstitutions> coupleOfInstitutionsList = new ArrayList<>();
        if(!institutions.isEmpty()) {
            for(int i=1; i<=institutions.size();i=i+2) {
                CoupleOfInstitutions coupleOfInstitutions = new CoupleOfInstitutions();
                Institution institution1 = institutions.get(i-1);
                coupleOfInstitutions.setInstitution1(institution1);
                Institution institution2 = new Institution("Tu może być Twoja Fundacja!","Napisz do nas!");
                if(institutions.size()>i) {
                    institution2 = institutions.get(i);
                }
                coupleOfInstitutions.setInstitution2(institution2);
                coupleOfInstitutionsList.add(coupleOfInstitutions);
            }
        }
        return coupleOfInstitutionsList;
    }

    public CoupleOfInstitutions() {
    }

    public CoupleOfInstitutions(Institution institution1, Institution institution2) {
        this.institution1 = institution1;
        this.institution2 = institution2;
    }

    @Override
    public String toString() {
        return "CoupleOfInstitutions{" +
                "institution1=" + institution1 +
                ", institution2=" + institution2 +
                '}';
    }
}
