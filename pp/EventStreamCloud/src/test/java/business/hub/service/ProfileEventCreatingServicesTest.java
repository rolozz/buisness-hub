package business.hub.service;

import business.hub.dto.DtoCreateEvent;
import business.hub.entity.ProfileEvent;
import business.hub.repository.ProfileRepository;
import business.hub.services.ProfileEventCreatingServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProfileEventCreatingServicesTest {

    @Mock
    private ProfileRepository profileRepository;

    @InjectMocks
    private ProfileEventCreatingServices profileEventCreatingServices;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveToDatabase() {
        // Given
        DtoCreateEvent dto = new DtoCreateEvent("accountId1", "firstName1", "lastName1");

        // When
        profileEventCreatingServices.saveToDatabase(dto);

        // Then
        ArgumentCaptor<ProfileEvent> profileEventCaptor = ArgumentCaptor.forClass(ProfileEvent.class);
        verify(profileRepository, times(1)).save(profileEventCaptor.capture());

        ProfileEvent capturedProfileEvent = profileEventCaptor.getValue();
        assertEquals(dto.getAccountId(), capturedProfileEvent.getAccountId());
        assertEquals(dto.getFirstName(), capturedProfileEvent.getFirstName());
        assertEquals(dto.getLastName(), capturedProfileEvent.getLastName());
    }
}
