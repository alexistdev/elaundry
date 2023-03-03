<?php

namespace App\Http\Requests;

use Illuminate\Foundation\Http\FormRequest;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;

class LaundryRequest extends FormRequest
{
    /**
     * Determine if the user is authorized to make this request.
     *
     * @return bool
     */
    public function authorize()
    {
        if (!Request::routeIs('adm.*')) {
            return false;
        }
        return Auth::check();
    }

    /**
     * Get the validation rules that apply to the request.
     *
     * @return array<string, mixed>
     */
    public function rules()
    {
        return [
            'namaPengguna' => 'required|max:255',
            'namaLaundry' => 'required|max:255',
            'emailPengguna' => 'required|email|unique:users,email',
            'phone' => 'required|max:255',
            'alamat' => 'required|max:1000',
        ];
    }

    public function messages()
    {
        return [
            'namaPengguna.required' => "Nama Pengguna Wajib diisi!",
            'namaPengguna.numeric' => "Panjang karakter maksimal 255 karakter!",
            'namaLaundry.required' => "Nama Laundry Wajib diisi!",
            'namaLaundry.numeric' => "Panjang karakter maksimal 255 karakter!",
            'emailPengguna.required' => "Email Wajib diisi!",
            'emailPengguna.email' => "Gunakan email yang valid!",
            'emailPengguna.unique' => "Email sudah pernah terdaftar!",
            'phone.required' => "Phone Wajib diisi!",
            'phone.numeric' => "Panjang karakter maksimal 255 karakter!",
            'alamat.required' => "Alamat Wajib diisi!",
            'alamat.numeric' => "Alamat karakter maksimal 255 karakter!",
        ];
    }
}
