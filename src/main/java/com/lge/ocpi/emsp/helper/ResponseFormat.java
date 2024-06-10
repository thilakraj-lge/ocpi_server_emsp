package com.lge.ocpi.emsp.helper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import com.lge.ocpi.emsp.model.dto.version.VersionDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The content that is sent with all the response messages is an 'application/json' type and contains a JSON object with
 * the following properties.
 * For errors on the HTTP layer, use HTTP error response codes, including the response format above,
 * that contains more details
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseFormat<T> {

    /**
     * OCPI status code, as listed in Status Codes {@link OcpiStatusCode}, indicates how the request was handled.
     * To avoid confusion with HTTP codes, OCPI status codes consist of four digits.
     */
    @JsonProperty("status_code")
    @NotNull
    @Min(value = 1000)
    @Max(value = 4999)
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private Integer statusCode;

    /**
     * An optional status message which may help when debugging.
     */
    @JsonProperty("status_message")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String statusMessage;

    /**
     * The time this message was generated.
     */
    @NotNull
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private LocalDateTime timestamp;

    /**
     * Contains the actual response data object or list of objects from each request
     */
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private T data;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String message;

    /**
     * Contains the actual response data object or list of objects from each request
     */
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private List<T> dataList;

    public ResponseFormat<T> build(OcpiStatusCode ocpiStatusCode) {
        this.setTimestamp(LocalDateTime.now());
        this.setStatusCode(ocpiStatusCode.getValue());
        this.setStatusMessage(ocpiStatusCode.getReasonPhrase());
        return this;
    }

    public ResponseFormat<T> build(OcpiStatusCode ocpiStatusCode, T data) {
        this.setData(data);
        this.setTimestamp(LocalDateTime.now());
        this.setStatusCode(ocpiStatusCode.getValue());
        this.setStatusMessage(ocpiStatusCode.getReasonPhrase());
        return this;
    }

    public ResponseFormat<T> build(OcpiStatusCode ocpiStatusCode, String message) {
        this.setTimestamp(LocalDateTime.now());
        this.setMessage(message);
        this.setStatusCode(ocpiStatusCode.getValue());
        this.setStatusMessage(ocpiStatusCode.getReasonPhrase());
        return this;
    }

    public ResponseFormat<T> build(HttpStatusCode httpStatusCode, String statusMessage) {
        this.setMessage(statusMessage);
        this.setTimestamp(LocalDateTime.now());
        this.setStatusCode(httpStatusCode.value());
        this.setStatusMessage(statusMessage);
        return this;
    }



    public ResponseFormat<T> build(OcpiStatusCode ocpiStatusCode, List<T> data) {
        this.setDataList(data);
        this.setTimestamp(LocalDateTime.now());
        this.setStatusCode(ocpiStatusCode.getValue());
        this.setStatusMessage(ocpiStatusCode.getReasonPhrase());
        return this;
    }
}
