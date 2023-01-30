package in.jt.binding;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerEvent {
	private Integer customerId;
	private String customerName;
	private LocalDateTime ldt;
}
