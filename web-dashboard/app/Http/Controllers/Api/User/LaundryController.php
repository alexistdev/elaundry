<?php

namespace App\Http\Controllers\Api\User;

use App\Http\Controllers\Controller;
use App\Models\Store;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Validator;

class LaundryController extends Controller
{
    public function list_laundry()
    {

        $data = Store::select('id', 'nama_laundry', 'phone', 'alamat', 'longitude', 'latitude')->orderBy('id', 'DESC')->where('status', 1)->get();
        if (!$data->isEmpty()) {
            return response()->json([
                'status' => true,
                'message' => "berhasil!",
                'data' => $data
            ], 200);
        }
        return response()->json([
            'status' => false,
            'message' => "data kosong!",
        ], 404);

    }
}
