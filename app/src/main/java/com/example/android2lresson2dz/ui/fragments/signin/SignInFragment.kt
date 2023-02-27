package com.example.android2lresson2dz.ui.fragments.signin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.android2lresson2dz.R
import com.example.android2lresson2dz.databinding.FragmentSignInBinding
import com.example.android2lresson2dz.utils.PreferenceHelper
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.concurrent.TimeUnit

class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding
    private var auth: FirebaseAuth? = null
    private var storedVerificationId: String? = ""
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        auth = Firebase.auth
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListener()
        init()
        su()
    }

    private fun init() {
        PreferenceHelper.unit(requireContext())
    }

    private fun setupListener() = with(binding) {
        sendPhoneNumber.setOnClickListener {
            if (phoneEt.text.isEmpty()) {
                if (phoneEt.text.isEmpty()) {
                    phoneEt.error = "asd1"
                }
            } else {
                startPhoneNumberVerification(phoneEt.text.toString())
            }
            sendCodeBtn.isVisible = true
        }
        sendCodeBtn.setOnClickListener {
            if (phoneEt.text.isEmpty() || codeEt.text.isEmpty()) {

                if (phoneEt.text.isEmpty()) {
                    phoneEt.error = "asd"

                    if (codeEt.text.isEmpty()) {
                        codeEt.error = "asd"
                    }
                }
            } else {
                verifyPhoneNumberWithCode(
                    storedVerificationId.toString(),
                    codeEt.text.toString()
                )
            }
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth?.signInWithCredential(credential)
            ?.addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    findNavController().navigate(R.id.action_signinsFragment_to_noteAppFragment)
                } else {
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(requireActivity(), "Registration is not", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
    }

    private fun verifyPhoneNumberWithCode(verificationId: String?, code: String) {
        val credential = PhoneAuthProvider.getCredential(verificationId!!, code)
        signInWithPhoneAuthCredential(credential)
    }

    private fun startPhoneNumberVerification(phoneNumber: String) {
        val options = PhoneAuthOptions.newBuilder(auth!!)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(requireActivity())
            .setCallbacks(callbacks)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private var callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            signInWithPhoneAuthCredential(credential)
        }

        override fun onVerificationFailed(p0: FirebaseException) {
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {
            storedVerificationId = verificationId
            resendToken = token
        }
    }

    private fun su() {
        PreferenceHelper.signIn = true
    }
}