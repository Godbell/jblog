package jblog.dto;

public class UserIdAvailabilityResponseDto {
    private boolean availability;

    public UserIdAvailabilityResponseDto(boolean availability) {
        this.availability = availability;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
