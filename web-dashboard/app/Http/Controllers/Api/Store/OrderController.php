<?php

namespace App\Http\Controllers\Api\Store;

use App\Http\Controllers\Controller;
use App\Models\Store;
use App\Models\Transaksi;
use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Validator;

class OrderController extends Controller
{
    public function get_order(Request $request)
    {
        $rules = [
            'user_id' => 'required|numeric',
        ];
        $validator = Validator::make($request->all(), $rules);
        if ($validator->fails()) {
            return response()->json([
                'status' => false,
                'message' => "data tidak lengkap",
            ], 404);
        } else {
            $cekUser = User::where('role_id',2)->find($request->user_id);
            if($cekUser!= null){
                $store = Store::where('user_id',$cekUser->id)->first();
                if($store != null){
                    $transaksi = Transaksi::with('customer')->where('store_id',$store->id)->get();
                    return response()->json([
                        'status' => true,
                        'message' => "berhasil",
                        'data' => $transaksi
                    ], 200);
                }
            }
            return response()->json([
                'status' => false,
                'message' => "tidak memiliki authorisasi",
            ], 401);
        }
    }
}
