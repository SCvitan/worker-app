package com.example.workapp.service;

import com.example.workapp.dto.AuthResponse;
import com.example.workapp.dto.AuthRequest;
import com.example.workapp.dto.UserCVProfileDTO;
import com.example.workapp.entity.TruckerProfile;
import com.example.workapp.entity.UserProfile;
import com.example.workapp.enums.UserRole;
import com.example.workapp.exceptions.UserAlreadyExistsException;
import com.example.workapp.repository.TruckerProfileRepo;
import com.example.workapp.repository.UserProfileRepo;
import com.example.workapp.security.JwtProvider;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UserProfileService {

    @Autowired
    private UserProfileRepo userProfileRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private CustomUserDetailsImp customUserDetailsImp;
    @Autowired
    private TruckerProfileRepo truckerProfileRepo;


    public AuthResponse registerUser(AuthRequest request){
        if (userProfileRepo.existsByEmail(request.getEmail())){
            throw new UserAlreadyExistsException(request.getEmail());
        }

        UserProfile userProfile = new UserProfile();
        userProfile.setEmail(request.getEmail());
        userProfile.setPassword(passwordEncoder.encode(request.getPassword()));
        userProfileRepo.save(userProfile);
        Authentication authentication = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Registration successful");
        authResponse.setRole(userProfile.getRole());

        return authResponse;
    }

    public AuthResponse login(AuthRequest request){

        String username = request.getEmail();
        Authentication authentication = authenticate(username, request.getPassword());

        Collection<? extends GrantedAuthority>authorities = authentication.getAuthorities();
        String role = authorities.isEmpty()?null:authorities.iterator().next().getAuthority();

        String jwt = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Login successful");
        authResponse.setRole(UserRole.valueOf(role));

        return authResponse;
    }

    @Transactional
    public void updateCV(UserCVProfileDTO userCVProfileDTO, String jwt) throws Exception {

        String email = jwtProvider.getEmailFromJwtToken(jwt);
        UserProfile userProfile = findUserByEmail(email);

        // Updating UserProfile fields
        userProfile.setFirstName(userCVProfileDTO.getFirstName());
        userProfile.setLastName(userCVProfileDTO.getLastName());
        userProfile.setGender(userCVProfileDTO.getGender());
        userProfile.setAge(userCVProfileDTO.getAge());
        userProfile.setPlaceOfResidence(userCVProfileDTO.getPlaceOfResidence());
        userProfile.setNationality(userCVProfileDTO.getNationality());
        userProfile.setEducationLevel(userCVProfileDTO.getEducationLevel());
        userProfile.setNameOfEducationalFacility(userCVProfileDTO.getNameOfEducationalFacility());
        userProfile.setDriversLicence(userCVProfileDTO.getDriversLicence());
        userProfile.setLanguages(userCVProfileDTO.getLanguages());
        userProfile.setProfession(userCVProfileDTO.getProfession());


        // Handling the TruckerProfile entity
        TruckerProfile truckerProfile = userProfile.getTruckerProfile();
        if (truckerProfile == null) {
            // Create a new TruckerProfile if it doesn't exist
            truckerProfile = new TruckerProfile();
            truckerProfile.setUserProfile(userProfile);
        }

        // Updating TruckerProfile fields

        truckerProfile.setYearsOfExperience(userCVProfileDTO.getYearsOfExperience());
        truckerProfile.setHazardousMaterialsCertified(userCVProfileDTO.getHazardousMaterialsCertified());
        truckerProfile.setPreviousEmployments(userCVProfileDTO.getPreviousEmployments());
        truckerProfile.setContractType(userCVProfileDTO.getContractType());
        truckerProfile.setStatesOfEmploymentOfInterest(userCVProfileDTO.getStatesOfEmploymentOfInterest());
        truckerProfile.setWillingToRelocate(userCVProfileDTO.getWillingToRelocate());
        truckerProfile.setLoadingUnloadingGoods(userCVProfileDTO.getLoadingUnloadingGoods());
        truckerProfile.setDriversLicences(userCVProfileDTO.getDriversLicences());
        truckerProfile.setJobInterest(userCVProfileDTO.getJobInterest());
        truckerProfile.setAccommodationCostsByEmployer(userCVProfileDTO.getAccommodationCostsByEmployer());
        truckerProfile.setExpectedNETSalary(userCVProfileDTO.getExpectedNETSalary());

        // Linking truckerProfile with userProfile
        userProfile.setTruckerProfile(truckerProfile);

        // Save userProfile (this will also save truckerProfile due to cascading)
        userProfileRepo.save(userProfile);

    }


    public UserProfile findUserByEmail(String email) throws Exception {

        UserProfile userProfile = userProfileRepo.findByEmail(email);

        if (userProfile==null){
            throw new Exception("User not found.");
        }

        return userProfile;
    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = customUserDetailsImp.loadUserByUsername(username);
        if (userDetails==null){
            throw new BadCredentialsException("Invalid username.");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("Invalid password.");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

}
