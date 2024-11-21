package pay.scope.payscope.ApiService;

import java.util.List;

import pay.scope.payscope.Model.QuickHistoryModel;
import pay.scope.payscope.Model.QuickResponceModel;
import pay.scope.payscope.Model.QuickTransferModel;
import pay.scope.payscope.Model.LoginModel;
import pay.scope.payscope.Model.LoginResponce;
import pay.scope.payscope.Model.LogoutResponse;
import pay.scope.payscope.Model.ManualRequest;
import pay.scope.payscope.Model.ManualRequestResponse;
import pay.scope.payscope.Model.OTPModel;
import pay.scope.payscope.Model.OTPResponse;
import pay.scope.payscope.Model.PaymentModesModel;
import pay.scope.payscope.Model.ResendOTPModel;
import pay.scope.payscope.Model.ResendOTPResponce;
import pay.scope.payscope.Model.SelectedBankModel;
import pay.scope.payscope.Model.TransactionModel;
import pay.scope.payscope.Model.VirtualCardModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface LoginService {
    @POST("mobile-api/login")
    Call<LoginResponce> createLogin(@Body LoginModel loginResModel);

    @POST("mobile-api/otp-verify")
    Call<OTPResponse> createOTP(@Body OTPModel otpResModel);

    @POST("mobile-api/resend-otp")
    Call<ResendOTPResponce> createResendOTP(@Body ResendOTPModel resendOTPModel);

    @GET("mobile-api/logout")
    Call<LogoutResponse> logout(@Header("Authorization") String token);

    @POST("mobile-api/fund/create-manual-request")
    Call<ManualRequestResponse> createManualResponse(@Body ManualRequest manualRequest, @Header("Authorization") String token);

    @GET("mobile-api/fund/virtul-request")
    Call<VirtualCardModel.Data> virtualCardModel(@Header("Authorization") String token);

    @GET("mobile-api/fund/manual-request")
    Call<TransactionModel> historyModel(@Header("Authorization") String token);

    @GET("mobile-api/bank-list")
    Call<List<SelectedBankModel>> selectedBankModel(@Header("Authorization") String token);

    @GET("mobile-api/payment-modes")
    Call<List<PaymentModesModel>> paymentModesModel(@Header("Authorization") String token);

    @POST("mobile-api/fund/create-payout-request")
    Call<QuickTransferModel> createQuickTransferRequest(@Body QuickResponceModel quickResponce, @Header("Authorization") String token);

    @GET("mobile-api/fund/payout-request")
    Call<QuickHistoryModel> quickHistoryResponce(@Header("Authorization") String token);


}
