package api.waits;

import io.restassured.response.Response;
import java.util.function.Supplier;

public class Wait {

	/**
	 * Waits until the API returns 200 OK status code, retrying every 2 seconds, up
	 * to a timeout.
	 *
	 * @param responseSupplier A lambda that supplies the Response (e.g.,
	 *                         StoreEndPointes::get_order)
	 * @param maxRetries       Number of retries before timeout
	 * @param waitMillis       Time to wait between retries (in milliseconds)
	 * @return The final Response that returned 200
	 * @throws RuntimeException if 200 is not returned within the retry limit
	 */
	public static Response waitUntilStatusCodeIs200(Supplier<Response> responseSupplier, int maxRetries,
			int waitMillis) {
		Response res = null;

		for (int i = 0; i < maxRetries; i++) {
			res = responseSupplier.get();
			if (res.getStatusCode() == 200) {
				return res;
			}

			try {
				Thread.sleep(waitMillis);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				throw new RuntimeException("Interrupted while waiting for API response", e);
			}
		}

		throw new RuntimeException("Timeout: API did not return 200 within " + maxRetries + " retries");

	}

}
