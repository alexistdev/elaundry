<?php

namespace Database\Seeders;

use App\Models\Customer;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;
use Illuminate\Support\Carbon;

class CustomerSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $date = Carbon::now()->format('Y-m-d H:i:s');
        $customer = [
            array('user_id' => '3','name'=> 'user', 'phone' => '08123456789','created_at' => $date,'updated_at' => $date),
            array('user_id' => '4','name'=> 'samantha', 'phone' => '08123456789','created_at' => $date,'updated_at' => $date),
        ];
        Customer::insert($customer);
    }
}
