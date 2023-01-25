<?php

namespace App\Http\Controllers\Admin;

use App\Http\Controllers\Controller;
use App\Http\Traits\AdminTrait;
use App\Models\Store;
use App\Models\User;
use Illuminate\Http\Request;
use Yajra\DataTables\DataTables;

class LaundryController extends Controller
{
    use AdminTrait;

    public function index(Request $request)
    {
//        return  $store = Store::all();
        if ($request->ajax()) {
            $store = Store::all();
//            $store = Store::orderBy('id','desc')->store->get();
            return DataTables::of($store)
                ->addIndexColumn()
//                ->editColumn('created_at', function ($request) {
//                    return $request->created_at->format('d-m-Y H:i:s');
//                })
//                ->editColumn('name', function ($request) {
//                    return ucwords($request->pelanggan?->name);
//                })
                ->addColumn('action', function ($row) {
//                    $url = route('', base64_encode($row->id));
//                    $url = "";
//                    $btn = " <a href=\"$url\" class=\"btn btn-outline-primary px-5\"> Edit</a>";
//                    $btn = $btn . " <a href=\"#\" class=\"btn btn-danger btn-sm ml-auto open-hapus\" data-id=\"$row->id\" data-bs-toggle=\"modal\" data-bs-target=\"#hapusModal\"><i class=\"fas fa-trash\"></i> Delete</i></a>";
                    return $btn = "";
                })
                ->rawColumns(['action'])
                ->make(true);
        }
        return view('admin.laundry', array(
            'judul' => "Dashboard Guru | FavoriteIDN",
            'menuUtama' => 'dashboard',
            'menuKedua' => 'dashboard',
        ));
    }
}
