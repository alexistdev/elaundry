<?php

namespace Database\Seeders;

use App\Models\Store;
use App\Models\User;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;
use Illuminate\Support\Carbon;
use Illuminate\Support\Facades\Hash;

class StoreSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $date = Carbon::now()->format('Y-m-d H:i:s');
        $store = [
            array('user_id' => '2', 'nama_laundry' => 'Adinda Laundry', 'phone' => '08123456789','alamat' => "Zainal Abidin,BandarLampung",'longitude' => "123456789",'latitude' => '12345678','harga_kiloan'=>5000,'created_at' => $date,'updated_at' => $date),
        ];
        Store::insert($store);
    }
}
