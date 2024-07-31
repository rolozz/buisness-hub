package business.hub.service;

import business.hub.dto.DtoCreateEvent;
import business.hub.services.ProfileEventCreatingServices;
import business.hub.services.StreamProcess;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.ValueMapper;
import org.apache.kafka.streams.kstream.ForeachAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class StreamProcessTest {

    private ProfileEventCreatingServices profileEventCreatingServices;
    private StreamProcess streamProcess;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        profileEventCreatingServices = mock(ProfileEventCreatingServices.class);
        streamProcess = new StreamProcess(profileEventCreatingServices);
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testProcess() {
        // Set up a mock KStream
        KStream<String, JsonNode> stream = mock(KStream.class);

        // Create a sample JsonNode
        JsonNode jsonNode = objectMapper.createObjectNode()
                .put("account_id", "testAccountId")
                .put("first_name", "testFirstName")
                .put("last_name", "testLastName");

        // Set up the ValueMapper to return the input value itself
        ValueMapper<JsonNode, JsonNode> valueMapper = value -> value;

        // Set up the ForeachAction to capture the processing logic
        ForeachAction<String, JsonNode> foreachAction = (key, value) -> {
            DtoCreateEvent dto = streamProcess.convertToDto(value);
            profileEventCreatingServices.saveToDatabase(dto);
        };

        // Set up the behavior for the stream to use the ValueMapper and ForeachAction
        when(stream.mapValues(any(ValueMapper.class))).thenReturn(stream);
        doAnswer(invocation -> {
            foreachAction.apply("testKey", jsonNode);
            return null;
        }).when(stream).foreach(any(ForeachAction.class));

        // Call the process method
        streamProcess.process(stream);

        // Verify the interactions
        verify(stream, times(1)).mapValues(any(ValueMapper.class));
        verify(stream, times(1)).foreach(any(ForeachAction.class));

        ArgumentCaptor<DtoCreateEvent> captor = ArgumentCaptor.forClass(DtoCreateEvent.class);
        verify(profileEventCreatingServices, times(1)).saveToDatabase(captor.capture());

        // Assert that the saved DTO has the expected values
        DtoCreateEvent capturedDto = captor.getValue();
        assertEquals("testAccountId", capturedDto.getAccountId());
        assertEquals("testFirstName", capturedDto.getFirstName());
        assertEquals("testLastName", capturedDto.getLastName());
    }
}


